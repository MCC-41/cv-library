/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Work;
import com.mii.cvlibrary.repositories.WorkRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class WorkService implements IService<Work, Integer>{

    @Autowired
    private WorkRepository wr;
    
    @Autowired
    private UserService us;
    
    @Override
    public List<Work> getAll() {
        return wr.findAll();
    }

    @Override
    public Work getById(Integer id) {
        return wr.getOne(id);
    }
    public List<Work> getByEmployee(Integer id) {
        return wr.getByEmployee(id);
    }
    @Override
    public Work insert(Work data) {
        return wr.save(data);
    }

    @Override
    public Work update(Integer id, Work data) {
        Work work = getById(id);
        work.setEmployee(new Employee(us.getId()));
        work.setCompanyName(data.getCompanyName());
        work.setPosition(data.getPosition());
        work.setJobDesc(data.getJobDesc());
        work.setStartDate(data.getStartDate());
        work.setEndDate(data.getEndDate());
        return wr.save(work);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            wr.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
