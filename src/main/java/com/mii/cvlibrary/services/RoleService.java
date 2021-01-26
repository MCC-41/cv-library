/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Role;
import com.mii.cvlibrary.repositories.RoleRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class RoleService implements IService<Role, Integer>{

    @Autowired
    private RoleRepository rr;
    @Override
    public List<Role> getAll() {
        return rr.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return rr.getOne(id);
    }

    @Override
    public Role insert(Role data) {
        return rr.save(data);
    }

    @Override
    public Role update(Integer id, Role data) {
        Role role = getById(id);
        role.setName(data.getName());
        return rr.save(role);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            rr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
