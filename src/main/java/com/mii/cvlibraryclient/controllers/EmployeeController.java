/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.ReligionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class EmployeeController {
    
    @Autowired 
    private EmployeeService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ReligionService religionService;
    
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String page(Model model){
        model.addAttribute("religions", religionService.getAll().getData());
        model.addAttribute("employees", service.getById(loginService.getIdEmployee()).getData());
        return "employee";
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody List<Employee> getAll(){
        return service.getAll().getData();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody ResponseData<Employee> getAll(@PathVariable Integer id){
        return service.getById(id);
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public @ResponseBody ResponseMessage<Employee> insert(@RequestBody Employee employee){
//        Employee employee = new Employee();
//        employee.setId(loginService.getIdEmployee());
//        employee.setEmployee(employee);
        return service.insert(employee);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public @ResponseBody ResponseMessage<Employee> update(@PathVariable Integer id,@RequestBody Employee employee){
        return service.update(id,employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    public @ResponseBody ResponseMessage<Employee> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
