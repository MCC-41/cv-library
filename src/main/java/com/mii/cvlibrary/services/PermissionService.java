/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Permission;
import com.mii.cvlibrary.repositories.PermissionRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class PermissionService implements IService<Permission, Integer>{

    @Autowired
    private PermissionRepository pr;
    
    @Override
    public List<Permission> getAll() {
        return pr.findAll();
    }

    @Override
    public Permission getById(Integer id) {
        return pr.getOne(id);
    }

    @Override
    public Permission insert(Permission data) {
        return pr.save(data);
    }

    @Override
    public Permission update(Integer id, Permission data) {
        Permission permission = getById(id);
        permission.setName(data.getName());
        return pr.save(permission);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            pr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
