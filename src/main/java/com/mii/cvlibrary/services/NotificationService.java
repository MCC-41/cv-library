/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.User;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class NotificationService {
    @Autowired
    private JavaMailSender jms;
    
    @Value("${spring.mail.username}")
    private String email;
    
    public void sendEmail(String string) throws MessagingException{
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(email);
        helper.setTo("muhamad.habibz08@gmail.com");
        helper.setSubject("Verifikasi Email");
        message.setText(string);
        jms.send(message);
    }
    
    public void sendMemo(String toEmail,String string) throws MessagingException{
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(email);
        helper.setTo(toEmail);
        helper.setSubject("MEMO CV EMPLOYEE");
        message.setText(string);
        jms.send(message);
    }
}
