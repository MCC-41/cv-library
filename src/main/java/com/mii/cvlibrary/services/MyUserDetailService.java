/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.MyUserDetail;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class MyUserDetailService implements UserDetailsService{
    @Autowired
    UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = new User();
        if(repository.getByUsername(string).isPresent()){
            user = repository.getByUsername(string).get();
        }else{
            System.out.println("aaa");
        }
        return new MyUserDetail(user);
    }
    
}
