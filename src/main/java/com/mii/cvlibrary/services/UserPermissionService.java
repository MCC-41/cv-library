/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.UserPermission;
import com.mii.cvlibrary.repositories.UserPermissionRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class UserPermissionService implements IService<UserPermission, Integer>{

    @Autowired
    private UserPermissionRepository upr;
    
    @Override
    public List<UserPermission> getAll() {
        return upr.findAll();
    }

    @Override
    public UserPermission getById(Integer id) {
        return upr.getOne(id);
    }

    @Override
    public UserPermission insert(UserPermission data) {
        return upr.save(data);
    }

    @Override
    public UserPermission update(Integer id, UserPermission data) {
        UserPermission userPermission = getById(id);
        userPermission.setUser(data.getUser());
        userPermission.setPermission(data.getPermission());
        return upr.save(userPermission);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            upr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
