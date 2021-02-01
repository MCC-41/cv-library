/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Education;
import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.EducationService;
import com.mii.cvlibraryclient.services.LevelService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.MajorService;
import com.mii.cvlibraryclient.services.UniversityService;
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
 * @author habib
 */
@Controller
@RequestMapping("education")
public class EducationController {
    
    @Autowired
    private EducationService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private LevelService levelService;
    
    @GetMapping("")
    public String page(Model model){
        model.addAttribute("universities", universityService.getAll().getData());
        model.addAttribute("majors", majorService.getAll().getData());
        model.addAttribute("levels", levelService.getAll().getData());
        return "employee-education";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Education> getAll(){
        return service.getAllEducation().getData();
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Education> insert(@RequestBody Education education){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        education.setEmployee(employee);
        return service.insert(education);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Education> update(@PathVariable Integer id,@RequestBody Education education){
        return service.update(id,education);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Education> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
