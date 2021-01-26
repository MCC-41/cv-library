/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.repositories.EmployeeRepository;
import com.mii.cvlibrary.services.iservices.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class EmployeeService implements IService<Employee, Integer>{
    @Autowired
    private EmployeeRepository er;

    @Override
    public List<Employee> getAll() {
        return er.findAll();
    }
    
    @Override
    public Employee getById(Integer id) {
        return er.getOne(id);
    }

    @Override
    public Employee insert(Employee data) {
        return er.save(data);
    }

    @Override
    public Employee update(Integer id, Employee data) {
        Employee employee = getById(id);
        employee.setName(data.getName());
        return er.save(employee);
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
