package com.itmo.ArtTrade.email;

import com.itmo.ArtTrade.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.MessageFormat;
import java.util.Properties;

public class MailService {

    private final JavaMailSenderImpl mailSender;

    public MailService() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);
        mailSender.setUsername("leon4ik25022002@gmail.com");
        mailSender.setPassword("****************");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        mailSender.setJavaMailProperties(props);
    }

    public void sendBidCreateNotification(String toEmail, User fromUser, float price, String title) {
        final SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("leon4ik25022002@gmail.com");
        simpleMail.setTo(toEmail);
        simpleMail.setSubject("Новое предложение по объявлению " + title);
        simpleMail.setText(MessageFormat.format("Пользователь {0} ({1}) предложил цену {2} рублей",
                fromUser.getName(), fromUser.getEmail(), String.valueOf(price)));
        mailSender.send(simpleMail);
    }

    public void sendBidDeleteNotification(String toEmail, User fromUser, float price, String title) {
        final SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("leon4ik25022002@gmail.com");
        simpleMail.setTo(toEmail);
        simpleMail.setSubject("Отменено предложение по объявлению " + title);
        simpleMail.setText(MessageFormat.format("Пользователь {0} ({1}) отменил предложение цены {2} рублей",
                fromUser.getName(), fromUser.getEmail(), String.valueOf(price)));
        mailSender.send(simpleMail);
    }
}
