/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.services.AwardService;
import com.mii.cvlibraryclient.services.EducationService;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.ExperienceService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.OrganizationService;
import com.mii.cvlibraryclient.services.TechnicalService;
import com.mii.cvlibraryclient.services.TrainingService;
import com.mii.cvlibraryclient.services.WorkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */
@RequestMapping("rm-employee")
@Controller
public class EmployeeRmController {
    
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private TechnicalService technicalService;
    @Autowired
    private WorkService workService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("/{id}")
    public String page(Model model, @PathVariable Integer id){
//        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("educations", educationService.getAllById(id));
//        model.addAttribute("levels",service.getAll().getData());
//        model.addAttribute("majors",serviceMajor.getAll().getData());
//        model.addAttribute("universities",serviceUniversity.getAll().getData());
        return "rm-employee-detail";
    }
    
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("employees" , employeeService.getAll().getData());
        System.out.println(employeeService.getAll().getData());
        return "rm-employee";
    }
    
    @GetMapping("detail")
    public String detail(Model model){
        return "rm-employee-detail";
    }
    
    @GetMapping("all")
    public @ResponseBody List<Employee> getAll(){
        return employeeService.getAll().getData();
    }
    
    
    
    
    
    
}
