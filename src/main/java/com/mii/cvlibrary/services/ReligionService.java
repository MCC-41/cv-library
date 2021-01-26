/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Religion;
import com.mii.cvlibrary.repositories.ReligionRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class ReligionService implements IService<Religion, Integer>{

    @Autowired
    private ReligionRepository rr;
    @Override
    public List<Religion> getAll() {
        return rr.findAll();
    }

    @Override
    public Religion getById(Integer id) {
        return rr.getOne(id);
    }

    @Override
    public Religion insert(Religion data) {
        return rr.save(data);
    }

    @Override
    public Religion update(Integer id, Religion data) {
        Religion religion = getById(id);
        religion.setName(data.getName());
        return rr.save(religion);
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
