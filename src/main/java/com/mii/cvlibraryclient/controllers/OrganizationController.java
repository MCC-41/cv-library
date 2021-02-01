/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Organization;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.OrganizationService;
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
@RequestMapping("organization")
public class OrganizationController {
    
    @Autowired
    private OrganizationService service;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("")
    public String experiencePage(){
        return "employee-organization";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Organization> getAll(){
        return service.getAllOrganization().getData();
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Organization> insert(@RequestBody Organization organization){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        organization.setEmployee(employee);
        return service.insert(organization);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Organization> update(@PathVariable Integer id,@RequestBody Organization organization){
        return service.update(id,organization);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Organization> delete(@PathVariable Integer id){
        return service.delete(id);
    }
    
}
