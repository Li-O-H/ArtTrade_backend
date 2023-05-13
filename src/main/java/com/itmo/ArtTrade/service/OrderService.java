package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderCreatePayload;
import com.itmo.ArtTrade.controller.payload.OrderUpdatePayload;
import com.itmo.ArtTrade.entity.*;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private CategoryService categoryService;
    private UserService userService;
    private AuthorizationService authorizationService;

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NoSuchDataException();
        }
        return order.get();
    }

    public List<Order> findActiveOrders(Float min, Float max, Long categoryId) {
        List<Order> orders;
        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            orders = orderRepository.findAllByStatusAndCategory(Status.ACTIVE, category);
        } else {
            orders = orderRepository.findAllByStatus(Status.ACTIVE);
        }
        if (min == null && max == null) {
            return orders;
        }
        if (max != null) {
            orders.removeIf(order -> {
                List<OrderBid> bids = order.getBids();
                bids.sort((i1, i2) -> Float.compare(i1.getPrice(), i2.getPrice()));
                return !bids.isEmpty() && bids.get(0).getPrice() > max;
            });
        }
        if (min != null) {
            orders.removeIf(order -> {
                List<OrderBid> bids = order.getBids();
                bids.sort((i1, i2) -> Float.compare(i1.getPrice(), i2.getPrice()));
                return !bids.isEmpty() && bids.get(0).getPrice() < min;
            });
        }
        return orders;
    }

    public List<Order> findUserOrders(Long userId) {
        User user = userService.findById(userId);
        try {
            authorizationService.invokerEqualsUserCheck(userId);
        } catch (Exception e) {
            return orderRepository.findAllByUserAndStatusNot(user, Status.HIDDEN);
        }
        return orderRepository.findAllByUser(user);
    }

    public List<Order> findFavoriteOrdersByUser(Long userId) {
        User user = userService.findById(userId);
        authorizationService.invokerEqualsUserCheck(userId);
        return orderRepository.findAllByFavoriteOfContains(user);
    }

    public List<Order> findDoneOrdersByUser(Long userId) {
        User user = userService.findById(userId);
        return orderRepository.findAllByDoneBy(user);
    }

    public Order save(OrderCreatePayload payload) {
        authorizationService.invokerEqualsUserCheck(payload.getUserId());
        User user = userService.findById(payload.getUserId());
        Category category = categoryService.findById(payload.getCategoryId());
        Order order = new Order()
                .setTitle(payload.getTitle())
                .setDescription(payload.getDescription())
                .setStatus(Status.HIDDEN)
                .setDeadline(payload.getDeadline())
                .setCategory(category)
                .setUser(user);
        return orderRepository.save(order);
    }

    public void addToFavorites(Long userId, Long orderId) {
        authorizationService.invokerEqualsUserCheck(userId);
        User user = userService.findById(userId);
        Order order = findById(orderId);
        authorizationService.invokerNotEqualsUserCheck(order.getUser().getId());
        if (order.getFavoriteOf().contains(user)) {
            return;
        }
        order.getFavoriteOf()
                .addAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.findById(u.getId());
                            uu.getFavoriteOrders().add(order);
                            return uu;
                        }).collect(Collectors.toList()));
        orderRepository.save(order);
    }

    public Order update(OrderUpdatePayload payload) {
        Order order = findById(payload.getId());
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        Category category = categoryService.findById(payload.getCategoryId());
        if (!order.getStatus().equals(Status.COMPLETED)){
            order
                    .setTitle(payload.getTitle())
                    .setDescription(payload.getDescription())
                    .setDeadline(payload.getDeadline())
                    .setCategory(category);
            return orderRepository.save(order);
        }
        return order;
    }

    public void activateOrder(Long id) {
        Order order = findById(id);
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        if (!order.getStatus().equals(Status.COMPLETED)){
            order.setStatus(Status.ACTIVE);
            orderRepository.save(order);
        }
    }

    public void hideOrder(Long id) {
        Order order = findById(id);
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        if (!order.getStatus().equals(Status.COMPLETED)){
            order.setStatus(Status.HIDDEN);
            orderRepository.save(order);
        }
    }

    public void completeOrder(Long id, String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new NoSuchDataException();
        }
        Order order = findById(id);
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        order.setStatus(Status.COMPLETED);
        order.setDoneBy(user);
        orderRepository.save(order);
    }

    public void deleteById(Long id) {
        Order order = findById(id);
        authorizationService.invokerEqualsUserCheck(order.getUser().getId());
        if (!order.getStatus().equals(Status.COMPLETED)){
            orderRepository.deleteById(id);
        }
    }

    public void deleteFromFavorites(Long userId, Long orderId) {
        authorizationService.invokerEqualsUserCheck(userId);
        User user = userService.findById(userId);
        Order order = findById(orderId);
        if (!order.getFavoriteOf().contains(user)) {
            return;
        }
        order.getFavoriteOf()
                .removeAll(Collections.singletonList(user)
                        .stream()
                        .map(u -> {
                            User uu = userService.findById(u.getId());
                            uu.getFavoriteOrders().remove(order);
                            return uu;
                        }).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
