package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.repository.ItemFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemFeedbackService {

    private ItemFeedbackRepository itemFeedbackRepository;
}
