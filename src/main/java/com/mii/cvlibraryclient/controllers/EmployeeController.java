/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.AwardService;
import com.mii.cvlibraryclient.services.EducationService;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.ExperienceService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.OrganizationService;
import com.mii.cvlibraryclient.services.ReligionService;
import com.mii.cvlibraryclient.services.TechnicalService;
import com.mii.cvlibraryclient.services.TrainingService;
import com.mii.cvlibraryclient.services.WorkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired 
    private EmployeeService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ReligionService religionService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private TechnicalService technicalService;
    @Autowired
    private WorkService workService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping("")
    public String page(Model model){
        model.addAttribute("religions", religionService.getAll().getData());
        model.addAttribute("employees", service.getById(loginService.getIdEmployee()).getData());
        return "employee";
    }
    
    @GetMapping("/detail")
    public String detail(Model model){
        model.addAttribute("employees", service.getById(loginService.getIdEmployee()).getData());
        model.addAttribute("educations", educationService.getAllEducation().getData());
        model.addAttribute("technicals", technicalService.getAllTechnical().getData());
        model.addAttribute("works", workService.getAllWork().getData());
        model.addAttribute("trainings", trainingService.getAllTraining().getData());
        model.addAttribute("organizations", organizationService.getAllOrganization().getData());
        model.addAttribute("experiences", experienceService.getAllExperience().getData());
        model.addAttribute("awards", awardService.getAllAward().getData());
        System.out.println(educationService.getAllEducation());
        return "employee-detail";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Employee> getAll(){
        return service.getAll().getData();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody ResponseData<Employee> getAll(@PathVariable Integer id){
        return service.getById(id);
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Employee> insert(@RequestBody Employee employee){
//        Employee employee = new Employee();
//        employee.setId(loginService.getIdEmployee());
//        employee.setEmployee(employee);
        return service.insert(employee);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Employee> update(@PathVariable Integer id,@RequestBody Employee employee){
        return service.update(id,employee);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Employee> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
