/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.services.EmployeeService;
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
@RequestMapping("rm")
@PreAuthorize("hasAnyRole('ROLE_RM')")
public class RmController {
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_RM')")
    public String page(Model model) {
        model.addAttribute("employees" , employeeService.getAll().getData());
        return "rm-employee";
    }
}
