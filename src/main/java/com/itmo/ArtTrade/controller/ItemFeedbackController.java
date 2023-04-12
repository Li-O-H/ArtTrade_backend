package com.itmo.ArtTrade.controller;

import com.itmo.ArtTrade.service.ItemFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemFeedback")
@AllArgsConstructor
public class ItemFeedbackController {

    private ItemFeedbackService itemFeedbackService;

}
