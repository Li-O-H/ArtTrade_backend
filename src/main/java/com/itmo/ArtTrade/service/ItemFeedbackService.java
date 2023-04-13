package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemFeedbackCreatePayload;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemFeedback;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.repository.ItemFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemFeedbackService {

    private ItemFeedbackRepository itemFeedbackRepository;
    private UserService userService;
    private ItemService itemService;

    public ItemFeedback save(ItemFeedbackCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Item item = itemService.findById(payload.getItemId());
        ItemFeedback itemFeedback = new ItemFeedback()
                .setItem(item)
                .setUser(user)
                .setText(payload.getText())
                .setRating(payload.getRating());
        return itemFeedbackRepository.save(itemFeedback);
    }

    public void deleteById(Long id) {
        itemFeedbackRepository.deleteById(id);
    }
}
