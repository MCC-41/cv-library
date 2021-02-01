/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Award;
import com.mii.cvlibrary.models.Education;
import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.repositories.AwardRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class AwardService implements IService<Award, Integer>{

    @Autowired
    private AwardRepository ar;
    @Autowired
    private UserService us;
    
    @Override
    public List<Award> getAll() {
        return ar.findAll();
    }

    @Override
    public Award getById(Integer id) {
        return ar.getOne(id);
    }
    
    public List<Award> getByEmployee(Integer id) {
        return ar.getByEmployee(id);
    }

    @Override
    public Award insert(Award data) {
        return ar.save(data);
    }

    @Override
    public Award update(Integer id, Award data) {
        Award award = getById(id);
        award.setEmployee(new Employee(us.getId()));
        award.setName(data.getName());
        award.setYear(data.getYear());
        return ar.save(award);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            ar.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
