package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.ItemBidCreatePayload;
import com.itmo.ArtTrade.email.JavaMailSenderConfig;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.ItemBidRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemBidService {

    private ItemBidRepository itemBidRepository;
    private UserService userService;
    private ItemService itemService;
    private AuthorizationService authorizationService;

    public ItemBid findById(Long id) {
        Optional<ItemBid> itemBid = itemBidRepository.findById(id);
        if (itemBid.isEmpty()) {
            throw new NoSuchDataException();
        }
        return itemBid.get();
    }

    private final JavaMailSender mailSender = new JavaMailSenderConfig().getJavaMailSender();

    public ItemBid save(ItemBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsOwnerCheck(user.getId());
        Item item = itemService.findById(payload.getItemId());
        ItemBid itemBid = new ItemBid()
                .setItem(item)
                .setUser(user)
                .setPrice(payload.getPrice());
        final SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("leon4ik25022002@gmail.com");
        simpleMail.setTo(item.getUser().getEmail());
        simpleMail.setSubject("Новое предложение по объявлению " + item.getTitle());
        simpleMail.setText(MessageFormat.format("Пользователь {0} ({1}) предложил цену {2} рублей", user.getName(), user.getEmail(), String.valueOf(itemBid.getPrice())));
        mailSender.send(simpleMail);
        return itemBidRepository.save(itemBid);
    }

    public void deleteById(Long id) {
        authorizationService.invokerEqualsOwnerCheck(findById(id).getUser().getId());
        itemBidRepository.deleteById(id);
    }
}
