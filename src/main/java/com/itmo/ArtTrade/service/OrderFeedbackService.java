package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderFeedbackCreatePayload;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderFeedback;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderFeedbackRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderFeedbackService {

    private OrderFeedbackRepository orderFeedbackRepository;
    private UserService userService;
    private OrderService orderService;
    private AuthorizationService authorizationService;

    public OrderFeedback findById(Long id) {
        Optional<OrderFeedback> orderFeedback = orderFeedbackRepository.findById(id);
        if (orderFeedback.isEmpty()) {
            throw new NoSuchDataException();
        }
        return orderFeedback.get();
    }

    public OrderFeedback save(OrderFeedbackCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsUserCheck(user.getId());
        Order order = orderService.findById(payload.getOrderId());
        authorizationService.invokerNotEqualsUserCheck(order.getUser().getId());
        OrderFeedback orderFeedback = new OrderFeedback()
                .setOrder(order)
                .setUser(user)
                .setText(payload.getText())
                .setRating(payload.getRating());
        return orderFeedbackRepository.save(orderFeedback);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(findById(id).getUser().getId());
        orderFeedbackRepository.deleteById(id);
    }
}
