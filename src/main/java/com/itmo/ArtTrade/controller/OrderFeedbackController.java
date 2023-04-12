package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.OrderFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderFeedback")
@AllArgsConstructor
public class OrderFeedbackController {

    private OrderFeedbackService orderFeedbackService;

}
