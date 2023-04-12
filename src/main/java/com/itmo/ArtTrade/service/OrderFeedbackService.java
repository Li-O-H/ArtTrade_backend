package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.OrderFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderFeedbackService {

    private OrderFeedbackRepository orderFeedbackRepository;
}
