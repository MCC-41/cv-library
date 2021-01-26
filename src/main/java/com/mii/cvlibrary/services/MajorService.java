/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Major;
import com.mii.cvlibrary.repositories.MajorRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class MajorService implements IService<Major, Integer>{

    @Autowired
    private MajorRepository mr;
    
    @Override
    public List<Major> getAll() {
        return mr.findAll();
    }

    @Override
    public Major getById(Integer id) {
        return mr.getOne(id);
    }

    @Override
    public Major insert(Major data) {
        return mr.save(data);
    }

    @Override
    public Major update(Integer id, Major data) {
        Major major = getById(id);
        major.setName(data.getName());
        return mr.save(major);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            mr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
