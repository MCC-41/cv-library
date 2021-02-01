/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Education;
import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.repositories.EducationRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class EducationService implements IService<Education, Integer>{

    @Autowired
    private EducationRepository er;
    @Autowired
    private UserService us;
    
    @Override
    public List<Education> getAll() {
        return er.findAll();
    }

    @Override
    public Education getById(Integer id) {
        return er.getOne(id);
    }
    
    public List<Education> getByEmployee(Integer id) {
        return er.getByEmployee(id);
    }

    @Override
    public Education insert(Education data) {
        data.setEmployee(new Employee(us.getId()));
        return er.save(data);
    }

    @Override
    public Education update(Integer id, Education data) {
        Education education = getById(id);
        education.setEmployee(new Employee(us.getId()));
        education.setUniversity(data.getUniversity());
        education.setMajor(data.getMajor());
        education.setLevel(data.getLevel());
        education.setIpk(data.getIpk());
        education.setYear(data.getYear());
        return er.save(education);
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
