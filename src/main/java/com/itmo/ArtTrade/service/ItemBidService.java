package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemBidCreatePayload;
import com.itmo.ArtTrade.email.MailService;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.ItemBidRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemBidService {

    private ItemBidRepository itemBidRepository;
    private UserService userService;
    private ItemService itemService;
    private AuthorizationService authorizationService;
    private final MailService mailService = new MailService();

    public ItemBid findById(Long id) {
        Optional<ItemBid> itemBid = itemBidRepository.findById(id);
        if (itemBid.isEmpty()) {
            throw new NoSuchDataException();
        }
        return itemBid.get();
    }

    public List<ItemBid> findUserItemBids(Long userId) {
        User user = userService.findById(userId);
        return itemBidRepository.findAllByUser(user);
    }

    public ItemBid save(ItemBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsUserCheck(user.getId());
        Item item = itemService.findById(payload.getItemId());
        ItemBid itemBid = new ItemBid()
                .setItem(item)
                .setUser(user)
                .setPrice(payload.getPrice());
        return itemBidRepository.save(itemBid);
    }

    public void deleteById(Long id) {
        ItemBid itemBid = findById(id);
        authorizationService.invokerEqualsUserCheck(itemBid.getUser().getId());
        itemBidRepository.deleteById(id);
        mailService.sendBidDeleteNotification(itemBid.getItem().getUser().getEmail(),
                itemBid.getUser(), itemBid.getPrice(), itemBid.getItem().getTitle());
    }
}
