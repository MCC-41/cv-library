/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("employee-all")
    @ResponseBody
    public ResponseData getEmployeeAll(){
        return new ResponseData(dashboardService.allEmployee().getData());
    }
    
    @GetMapping("user-all")
    @ResponseBody
    public ResponseData getUserAll(){
        return new ResponseData(dashboardService.allUser().getData());
    }
    
}
