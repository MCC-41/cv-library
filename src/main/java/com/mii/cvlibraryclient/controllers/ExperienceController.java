/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Experience;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.ExperienceService;
import com.mii.cvlibraryclient.services.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("experience")
public class ExperienceController {
    
    @Autowired
    private ExperienceService service;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("")
    public String experiencePage(){
        return "employee-experience";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Experience> getAll(){
        return service.getAllExperience().getData();
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Experience> insert(@RequestBody Experience experience){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        experience.setEmployee(employee);
        return service.insert(experience);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Experience> update(@PathVariable Integer id,@RequestBody Experience experience){
        return service.update(id,experience);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Experience> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
