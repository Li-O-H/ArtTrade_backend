package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderFeedbackCreatePayload;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderFeedback;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.repository.OrderFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderFeedbackService {

    private OrderFeedbackRepository orderFeedbackRepository;
    private UserService userService;
    private OrderService orderService;

    public OrderFeedback save(OrderFeedbackCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Order order = orderService.findById(payload.getOrderId());
        OrderFeedback orderFeedback = new OrderFeedback()
                .setOrder(order)
                .setUser(user)
                .setText(payload.getText())
                .setRating(payload.getRating());
        return orderFeedbackRepository.save(orderFeedback);
    }

    public void deleteById(Long id) {
        orderFeedbackRepository.deleteById(id);
    }
}
