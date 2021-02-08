/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services;

import com.mii.cvlibrary.repositories.EmployeeRepository;
import com.mii.cvlibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author habib
 */
@Service
public class DashboardService {
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    UserRepository userRepository;
    
    public int totalEmployee() {
        return employeeRepository.totalEmployee();
    }
    
    public int totalUser() {
        return userRepository.totalUser();
    }
}
