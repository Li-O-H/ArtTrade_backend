package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.entity.ItemFeedback;
import com.itmo.ArtTrade.repository.ItemFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemFeedbackService {

    private ItemFeedbackRepository itemFeedbackRepository;

    public ItemFeedback save(ItemFeedback itemFeedback) {
        itemFeedback.setId(null);
        return itemFeedbackRepository.save(itemFeedback);
    }

    public void deleteById(Long id) {
        itemFeedbackRepository.deleteById(id);
    }
}
