/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Training;
import com.mii.cvlibrary.repositories.TrainingRepository;
import com.mii.cvlibrary.repositories.UserRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class TrainingService implements IService<Training, Integer>{

    @Autowired
    private TrainingRepository tr;
    @Autowired
    private UserService us;
    
    @Override
    public List<Training> getAll() {
        return tr.findAll();
    }

    @Override
    public Training getById(Integer id) {
        return tr.getOne(id);
    }

    @Override
    public Training insert(Training data) {
        return tr.save(data);
    }

    @Override
    public Training update(Integer id, Training data) {
        Training training = getById(id);
        training.setEmployee(new Employee(us.getId()));
        training.setName(data.getName());
        training.setInstitution(data.getInstitution());
        training.setYear(data.getYear());
        training.setTrainingType(data.getTrainingType());
        training.setFile(data.getFile());
        return tr.save(training);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            tr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
