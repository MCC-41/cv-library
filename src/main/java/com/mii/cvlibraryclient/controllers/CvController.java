/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.LevelService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.MajorService;
import com.mii.cvlibraryclient.services.PermissionService;
import com.mii.cvlibraryclient.services.RoleService;
import com.mii.cvlibraryclient.services.UniversityService;
import com.mii.cvlibraryclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adhi
 */

@Controller
@RequestMapping("")
public class CvController {
    
    @Autowired
    private LevelService service;
    @Autowired
    private MajorService serviceMajor;
    @Autowired
    private UniversityService serviceUniversity;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService userPermissionService;
    @Autowired
    private LoginService loginService;
    
    
    @GetMapping("dashboard")
    public String dash() {
        return "dashboard";
    }
//    
//    @GetMapping("admin")
//    public String admin() {
//        return "admin-user";
//    }
    
    @GetMapping("adminrole")
    public String adminRole(Model model) {
        model.addAttribute("roles", roleService.getAll().getData());
        model.addAttribute("users" , userService.getAll().getData());
        return "admin-role";
    }
    
    @GetMapping("adminpermission")
    public String adminPermission(Model model) {
        model.addAttribute("permissions", userPermissionService.getAll().getData());
        model.addAttribute("users" , userService.getAll().getData());
        return "admin-permission";
    }
    
//    @GetMapping("adminreligion")
//    public String adminReligion() {
//        return "admin-religion";
//    }
    
//    @GetMapping("adminedu")
//    public String adminEdu(Model model) {
//        model.addAttribute("levels",service.getAll().getData());
//        model.addAttribute("majors",serviceMajor.getAll().getData());
//        model.addAttribute("universities",serviceUniversity.getAll().getData());
//        System.out.println(service.getAll().getData());
//        return "admin-edu";
//    }
    
    @GetMapping("admintech")
    public String adminTech() {
        return "admin-tech";
    }
    
    @GetMapping("admintraining")
    public String adminTraining() {
        return "admin-training";
    }
    
//    @GetMapping("employee")
//    public String employeePersonal(){
//        return "employee";
//    }
    
    @GetMapping("employee-edu")
    public String employeeEducation(){
        return "employee-education";
    }
    
    @GetMapping("employee-tech")
    public String employeeTechnical(){
        return "employee-technical";
    }
    
    @GetMapping("employee-work")
    public String employeeWork(){
        return "employee-work";
    }
    
    @GetMapping("employee-training")
    public String employeeTraining(){
        return "employee-training";
    }
    
    @GetMapping("employee-organization")
    public String employeeOrganization(){
        return "employee-organization";
    }
    
    @GetMapping("employee-exp")
    public String employeeExperience(){
        return "employee-experience";
    }
    
    @GetMapping("employee-awards")
    public String employeeAwards(){
        return "employee-awards";
    }
    
//    @GetMapping("employee-detail")
//    public String tesCV(Model model){
//        model.addAttribute("employees", employeeService.getById(loginService.getIdEmployee()).getData());
//        System.out.println(employeeService.getById(6).getData());
//        
//        
//        return "employee-detail";
//    }
    
//    @GetMapping("rm-employee")
//    public String rmEmployee(Model model){
////        model.addAttribute("employees" , employeeService.getAll());
//        return "rm-employee";
//    }
    
//    @GetMapping("rm-employee-eye/{id}")
//    public String rmDetail(Model model,@PathVariable Integer id){
//        model.addAttribute("levels",service.getAll().getData());
//        model.addAttribute("majors",serviceMajor.getAll().getData());
//        model.addAttribute("universities",serviceUniversity.getAll().getData());
//        return "rm-employee-detail";
//    }
    
    @GetMapping("print")
    public String print(){
        return "page-cv";
    }
    
    
    
    
}
