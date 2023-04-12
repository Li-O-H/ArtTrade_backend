package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.OrderFeedback;
import com.itmo.ArtTrade.repository.OrderFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderFeedbackService {

    private OrderFeedbackRepository orderFeedbackRepository;

    public OrderFeedback save(OrderFeedback orderFeedback) {
        orderFeedback.setId(null);
        return orderFeedbackRepository.save(orderFeedback);
    }

    public void deleteById(Long id) {
        orderFeedbackRepository.deleteById(id);
    }
}
