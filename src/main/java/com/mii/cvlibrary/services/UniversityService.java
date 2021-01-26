/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.University;
import com.mii.cvlibrary.repositories.UniversityRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class UniversityService implements IService<University, Integer>{

    @Autowired
    private UniversityRepository ur;
    
    @Override
    public List<University> getAll() {
        return ur.findAll();
    }

    @Override
    public University getById(Integer id) {
        return ur.getOne(id);
    }

    @Override
    public University insert(University data) {
        return ur.save(data);
    }

    @Override
    public University update(Integer id, University data) {
        University university = getById(id);
        university.setName(data.getName());
        return ur.save(university);
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
