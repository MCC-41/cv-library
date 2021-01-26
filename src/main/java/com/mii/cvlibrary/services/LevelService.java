/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Level;
import com.mii.cvlibrary.repositories.LevelRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class LevelService implements IService<Level, Integer>{

    @Autowired
    private LevelRepository lr;
    
    @Override
    public List<Level> getAll() {
        return lr.findAll();
    }

    @Override
    public Level getById(Integer id) {
        return lr.getOne(id);
    }

    @Override
    public Level insert(Level data) {
        return lr.save(data);
    }

    @Override
    public Level update(Integer id, Level data) {
        Level level = getById(id);
        level.setName(data.getName());
        return lr.save(level);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            lr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
