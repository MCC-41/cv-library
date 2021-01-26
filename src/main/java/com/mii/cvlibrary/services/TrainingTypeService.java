/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.TrainingType;
import com.mii.cvlibrary.repositories.TrainingTypeRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class TrainingTypeService implements IService<TrainingType, Integer>{

    @Autowired
    private TrainingTypeRepository ttr;
    
    @Override
    public List<TrainingType> getAll() {
        return ttr.findAll();
    }

    @Override
    public TrainingType getById(Integer id) {
        return ttr.getOne(id);
    }

    @Override
    public TrainingType insert(TrainingType data) {
        return ttr.save(data);
    }

    @Override
    public TrainingType update(Integer id, TrainingType data) {
        TrainingType trainingType = getById(id);
        trainingType.setName(data.getName());
        return ttr.save(trainingType);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            ttr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
