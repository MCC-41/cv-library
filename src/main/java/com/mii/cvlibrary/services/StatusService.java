/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Status;
import com.mii.cvlibrary.repositories.StatusRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class StatusService implements IService<Status, Integer>{

    @Autowired
    private StatusRepository sr;
    
    @Override
    public List<Status> getAll() {
        return sr.findAll();
    }

    @Override
    public Status getById(Integer id) {
        return sr.getOne(id);
    }

    @Override
    public Status insert(Status data) {
        return sr.save(data);
    }

    @Override
    public Status update(Integer id, Status data) {
        Status status = getById(id);
        status.setName(data.getName());
        return sr.save(status);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            sr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
