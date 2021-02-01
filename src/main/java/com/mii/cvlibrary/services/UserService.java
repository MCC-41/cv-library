/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Status;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.repositories.UserRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class UserService implements IService<User, Integer>{
    @Autowired
    private UserRepository ur;
    
    public User getByUsername(String name){
        return ur.getByUsername(name).get();
    }
    
    public void isNotValid(User user){
        Status status = new Status();
        status.setId(user.getStatus().getId()+1);
        user.setStatus(status);
        if(user.getStatus().getId()==3){
            user.setVerified(false);
        }
        ur.save(user);
    }
    public void isValid(User user){
        if(user.getStatus().getId()!=3){
            Status status = new Status();
            status.setId(0);
            user.setStatus(status);
        }
        ur.save(user);
    }
    
    public Integer getId(){
        User id = getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return id.getId();
    }
    
    public User insertWithEmployee(String email,String name){
        User user = new User();
        return user;
    }

    public Integer getIdByUsername(String username){
        User id = getByUsername(username);
        return id.getId();
    }
    
    @Override
    public List<User> getAll() {
        return ur.findAll();
    }

    @Override
    public User getById(Integer id) {
        return ur.getOne(id);
    }

    @Override
    public User insert(User data) {
        return ur.save(data);
    }

    @Override
    public User update(Integer id, User data) {
        User user = getById(id);
        user.setEmployee(new Employee(getId()));
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setStatus(data.getStatus());
        user.setVerified(data.getIsVerified());
        return ur.save(user);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            ur.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
