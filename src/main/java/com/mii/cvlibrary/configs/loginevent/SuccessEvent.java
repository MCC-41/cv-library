/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.configs.loginevent;

import com.mii.cvlibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author habib
 */
@Component
public class SuccessEvent implements ApplicationListener<AuthenticationSuccessEvent>{
    @Autowired
    private UserService service;
    
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        System.out.println("berhasil");
        service.isValid(service.getByUsername(e.getAuthentication().getName()));
    }
    
}
