/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Technical;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.TechnicalService;
import com.mii.cvlibraryclient.services.TechnicalTypeService;
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
@RequestMapping("technical")
public class TechnicalController {
    
    @Autowired
    private TechnicalService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TechnicalTypeService typeService;
    
    @GetMapping("")
    public String page(Model model){
        model.addAttribute("types", typeService.getAll().getData());
        return "employee-technical";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Technical> getAll(){
        return service.getAllTechnical().getData();
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Technical> insert(@RequestBody Technical technical){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        technical.setEmployee(employee);
        return service.insert(technical);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Technical> update(@PathVariable Integer id,@RequestBody Technical technical){
        return service.update(id,technical);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Technical> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
