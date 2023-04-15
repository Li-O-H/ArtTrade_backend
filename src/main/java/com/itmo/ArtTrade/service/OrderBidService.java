package com.itmo.ArtTrade.service;

import com.itmo.ArtTrade.controller.payload.OrderBidCreatePayload;
import com.itmo.ArtTrade.email.JavaMailSenderConfig;
import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.exception.NoSuchDataException;
import com.itmo.ArtTrade.repository.OrderBidRepository;
import com.itmo.ArtTrade.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderBidService {

    private OrderBidRepository orderBidRepository;
    private UserService userService;
    private OrderService orderService;
    private AuthorizationService authorizationService;

    public OrderBid findById(Long id) {
        Optional<OrderBid> orderBid = orderBidRepository.findById(id);
        if (orderBid.isEmpty()) {
            throw new NoSuchDataException();
        }
        return orderBid.get();
    }

    private final JavaMailSender mailSender = new JavaMailSenderConfig().getJavaMailSender();

    public OrderBid save(OrderBidCreatePayload payload) {
        User user = userService.findById(payload.getUserId());
        authorizationService.invokerEqualsUserCheck(user.getId());
        Order order = orderService.findById(payload.getOrderId());
        authorizationService.invokerNotEqualsUserCheck(order.getUser().getId());
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
        authorizationService.invokerEqualsUserCheck(findById(id).getUser().getId());
        orderBidRepository.deleteById(id);
    }
}
