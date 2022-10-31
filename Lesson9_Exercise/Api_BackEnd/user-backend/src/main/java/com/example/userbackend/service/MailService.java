package com.example.userbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailService {
    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/send-simple-email")
    public String sendSimpleEmail(String email,String subject,String body) {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        //Email mình gửi tới
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        // Send Message!
        emailSender.send(message);

        return "Email Sent!";
    }
}
