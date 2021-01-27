/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.NotificationService;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationService ns;
    
    @PostMapping("send")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    public ResponseRest<String> sendEmail() throws MessagingException{
        ns.sendEmail("halo");
        return ResponseRest.success("berhasil");
    }
}
