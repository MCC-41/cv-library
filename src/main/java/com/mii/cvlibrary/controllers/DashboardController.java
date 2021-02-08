/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.models.data.ResponseData;
import com.mii.cvlibrary.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
@RequestMapping("api/dashboard")
public class DashboardController {
    
    @Autowired
    DashboardService dashboardService;
    
    @GetMapping("total_employee")
    public ResponseData getTotalEmp() {
        return new ResponseData(dashboardService.totalEmployee());
    }
    
    @GetMapping("total_user")
    public ResponseData getTotalUser() {
        return new ResponseData(dashboardService.totalUser());
    }
    
}
