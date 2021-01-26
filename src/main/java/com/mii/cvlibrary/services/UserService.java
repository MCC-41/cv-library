/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Status;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class UserService {
    @Autowired
    private UserRepository ur;
    
    public User getByUsername(String name){
        return ur.getByUsername(name).get();
    }
    
    public void isNotValid(User user){
        Status status = new Status();
        status.setId(user.getIdStatus().getId()+1);
        user.setIdStatus(status);
        if(user.getIdStatus().getId()==3){
            user.setIsVerified(false);
        }
        ur.save(user);
    }
    public void isValid(User user){
        if(user.getIdStatus().getId()!=3){
            Status status = new Status();
            status.setId(0);
            user.setIdStatus(status);
        }
        ur.save(user);
    }
}
