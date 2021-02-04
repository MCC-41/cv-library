/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("detail")
public class EmployeeDetailController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String page(Model model) {
//        model.addAttribute("photo", service.getdown());
        return "employee-detail";
    }
}
