/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adhi
 */

@Controller
@RequestMapping("")
public class CvController {
    
    
    @GetMapping("dashboard")
    public String dash() {
        return "dashboard";
    }
       @GetMapping("admin")
    public String admin() {
        return "admin-user";
    }
    
    @GetMapping("adminrole")
    public String adminRole() {
        return "admin-role";
    }
    
    @GetMapping("adminpermission")
    public String adminPermission() {
        return "admin-permission";
    }
    
    @GetMapping("adminreligion")
    public String adminReligion() {
        return "admin-religion";
    }
    
    @GetMapping("adminedu")
    public String adminEdu() {
        return "admin-edu";
    }
    
    @GetMapping("admintech")
    public String adminTech() {
        return "admin-tech";
    }
    
    @GetMapping("admintraining")
    public String adminTraining() {
        return "admin-training";
    }
    
}
