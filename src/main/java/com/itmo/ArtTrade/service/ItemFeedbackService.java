package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemFeedbackCreatePayload;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemFeedback;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.ItemFeedbackRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemFeedbackService {

    private ItemFeedbackRepository itemFeedbackRepository;
    private UserService userService;
    private ItemService itemService;
    private AuthorizationService authorizationService;

    public ItemFeedback findById(Long id) {
        Optional<ItemFeedback> itemFeedback = itemFeedbackRepository.findById(id);
        if (itemFeedback.isEmpty()) {
            throw new NoSuchDataException();
        }
        return itemFeedback.get();
    }

    public List<ItemFeedback> findUserItemFeedbacks(Long userId) {
        User user = userService.findById(userId);
        return itemFeedbackRepository.findAllByUser(user);
    }

    public ItemFeedback save(ItemFeedbackCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsUserCheck(user.getId());
        Item item = itemService.findById(payload.getItemId());
        authorizationService.invokerNotEqualsUserCheck(item.getUser().getId());
        ItemFeedback itemFeedback = new ItemFeedback()
                .setItem(item)
                .setUser(user)
                .setText(payload.getText())
                .setRating(payload.getRating());
        return itemFeedbackRepository.save(itemFeedback);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsUserCheck(findById(id).getUser().getId());
        itemFeedbackRepository.deleteById(id);
    }
}
