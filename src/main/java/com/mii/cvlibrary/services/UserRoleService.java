/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Role;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.models.UserRole;
import com.mii.cvlibrary.repositories.UserRoleRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class UserRoleService implements IService<UserRole, Integer>{

    @Autowired
    private UserRoleRepository urr;
    @Autowired
    private UserService us;
    
    @Override
    public List<UserRole> getAll() {
        return urr.findAll();
    }

    @Override
    public UserRole getById(Integer id) {
        return urr.getOne(id);
    }

    @Override
    public UserRole insert(UserRole data) {
        return urr.save(data);
    }

    @Override
    public UserRole update(Integer id, UserRole data) {
        UserRole userRole = getById(id);
        userRole.setUser(data.getUser());
        userRole.setRole(data.getRole());
        return urr.save(userRole);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            urr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
