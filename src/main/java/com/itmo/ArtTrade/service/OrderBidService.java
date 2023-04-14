package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.email.JavaMailSenderConfig;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.repository.OrderBidRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;
    private UserService userService;
    private OrderService orderService;

    private final JavaMailSender mailSender = new JavaMailSenderConfig().getJavaMailSender();

    public OrderBid save(OrderBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        Order order = orderService.findById(payload.getOrderId());
        OrderBid orderBid = new OrderBid()
                .setOrder(order)
                .setUser(user)
                .setPrice(payload.getPrice());
        final SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("leon4ik25022002@gmail.com");
        simpleMail.setTo(order.getUser().getEmail());
        simpleMail.setSubject("Новое предложение по объявлению " + order.getTitle());
        simpleMail.setText(MessageFormat.format("Пользователь {0} ({1}) предложил цену {2} рублей", user.getName(), user.getEmail(), String.valueOf(orderBid.getPrice())));
        mailSender.send(simpleMail);
        return orderBidRepository.save(orderBid);
    }

    public void deleteById(Long id) {
        orderBidRepository.deleteById(id);
    }
}
