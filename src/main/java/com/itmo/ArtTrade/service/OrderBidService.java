package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.email.MailService;
import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderBidRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;
    private UserService userService;
    private OrderService orderService;
    private AuthorizationService authorizationService;
    private final MailService mailService = new MailService();

    public OrderBid findById(Long id) {
        Optional<OrderBid> orderBid = orderBidRepository.findById(id);
        if (orderBid.isEmpty()) {
            throw new NoSuchDataException();
        }
        return orderBid.get();
    }

    public List<OrderBid> findUserOrderBids(Long userId) {
        User user = userService.findById(userId);
        return orderBidRepository.findAllByUser(user);
    }

    public List<OrderBid> findOrderOrderBids(Long orderId) {
        Order order = orderService.findById(orderId);
        return orderBidRepository.findAllByOrder(order);
    }

    public OrderBid save(OrderBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsUserCheck(user.getId());
        Order order = orderService.findById(payload.getOrderId());
        OrderBid orderBid = new OrderBid()
                .setOrder(order)
                .setPrice(payload.getPrice());
        mailService.sendBidCreateNotification(order.getUser().getEmail(), user, payload.getPrice(), order.getTitle());
        return orderBidRepository.save(orderBid);
    }

    public void deleteById(Long id) {
        OrderBid orderBid = findById(id);
        authorizationService.invokerEqualsUserCheck(orderBid.getUser().getId());
        orderBidRepository.deleteById(id);
        mailService.sendBidDeleteNotification(orderBid.getOrder().getUser().getEmail(),
                orderBid.getUser(), orderBid.getPrice(), orderBid.getOrder().getTitle());
    }
}
