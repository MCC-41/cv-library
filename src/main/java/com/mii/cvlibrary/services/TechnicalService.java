/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Technical;
import com.mii.cvlibrary.repositories.TechnicalRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class TechnicalService implements IService<Technical, Integer>{

    @Autowired
    private TechnicalRepository tr;
    @Autowired
    private UserService us;
    
    @Override
    public List<Technical> getAll() {
        return tr.findAll();
    }

    @Override
    public Technical getById(Integer id) {
        return tr.getOne(id);
    }
    public List<Technical> getByEmployee(Integer id) {
        return tr.getByEmployee(id);
    }
    @Override
    public Technical insert(Technical data) {
        return tr.save(data);
    }

    @Override
    public Technical update(Integer id, Technical data) {
        Technical technical = getById(id);
        technical.setEmployee(new Employee(us.getId()));
        technical.setName(data.getName());
        technical.setTechnicalType(data.getTechnicalType());
        return tr.save(technical);
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
