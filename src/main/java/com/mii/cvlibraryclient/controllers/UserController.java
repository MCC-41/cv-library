/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Status;
import com.mii.cvlibraryclient.models.User;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.StatusService;
import com.mii.cvlibraryclient.services.UserService;
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
 * @author habib
 */
@Controller
@RequestMapping("user")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private StatusService statusService;
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("")
    public String user(Model model){
        model.addAttribute("users", service.getAll().getData());
        return "admin-user";
    }
    @GetMapping("employee")
    public @ResponseBody ResponseList<Employee> employee(){
        return employeeService.getAll();
    }
    @GetMapping("status")
    public @ResponseBody ResponseList<Status> status(){
        return statusService.getAll();
    }
    @PostMapping("add")
    public @ResponseBody ResponseMessage<User> addUser(@RequestBody User user){
        return service.insert(user);
    }
    
    @PutMapping("{id}")
    public @ResponseBody ResponseMessage<User> update(@PathVariable Integer id, User user){
        return service.update(id, user);
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody ResponseMessage<User> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}

