/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.WorkProjectSpec;
import com.mii.cvlibrary.repositories.WorkProjectSpecRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class WorkProjectSpecService implements IService<WorkProjectSpec, Integer>{

    @Autowired
    private WorkProjectSpecRepository repository;
    
    @Override
    public List<WorkProjectSpec> getAll() {
        return repository.findAll();
    }

    @Override
    public WorkProjectSpec getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public WorkProjectSpec insert(WorkProjectSpec data) {
        return repository.save(data);
    }

    @Override
    public WorkProjectSpec update(Integer id, WorkProjectSpec data) {
        WorkProjectSpec spec = getById(id);
        spec.setName(data.getName());
        return repository.save(spec);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            repository.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
