package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.*;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderRepository;
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

    public Order save(Order order) {
        order.setId(null);
        return orderRepository.save(order);
    }

    public void addToFavorites(Long userId, Long orderId) {
        User user = userService.findById(userId);
        Order order = findById(orderId);
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

    public Order update(Order newOrder) {
        Order order = findById(newOrder.getId());
        if (!order.getStatus().equals(Status.COMPLETED)){
            order.setTitle(newOrder.getTitle()).setDescription(newOrder.getDescription()).setDeadline(newOrder.getDeadline());
            return orderRepository.save(order);
        }
        return order;
    }

    public void activateOrder(Long id) {
        Order order = findById(id);
        if (!order.getStatus().equals(Status.COMPLETED)){
            order.setStatus(Status.ACTIVE);
            orderRepository.save(order);
        }
    }

    public void hideOrder(Long id) {
        Order order = findById(id);
        if (!order.getStatus().equals(Status.COMPLETED)){
            order.setStatus(Status.HIDDEN);
            orderRepository.save(order);
        }
    }

    public void completeOrder(Long id, Long userId) {
        User user = userService.findById(userId);
        Order order = findById(id);
        order.setStatus(Status.COMPLETED);
        order.setDoneBy(user);
        orderRepository.save(order);
    }

    public void deleteById(Long id) {
        if (!findById(id).getStatus().equals(Status.COMPLETED)){
            orderRepository.deleteById(id);
        }
    }

    public void deleteFromFavorites(Long userId, Long orderId) {
        User user = userService.findById(userId);
        Order order = findById(orderId);
        if (order.getFavoriteOf().contains(user)) {
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
