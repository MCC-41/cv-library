/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Experience;
import com.mii.cvlibrary.repositories.ExperienceRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class ExperienceService implements IService<Experience, Integer>{

    @Autowired
    private ExperienceRepository er;
    @Autowired
    private UserService us;
    
    @Override
    public List<Experience> getAll() {
        return er.findAll();
    }

    @Override
    public Experience getById(Integer id) {
        return er.getOne(id);
    }
    public List<Experience> getByEmployee(Integer id) {
        return er.getByEmployee(id);
    }
    @Override
    public Experience insert(Experience data) {
        return er.save(data);
    }

    @Override
    public Experience update(Integer id, Experience data) {
        Experience experience = getById(id);
        experience.setEmployee(new Employee(us.getId()));
        experience.setName(data.getName());
        experience.setYear(data.getYear());
        return er.save(experience);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            er.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
