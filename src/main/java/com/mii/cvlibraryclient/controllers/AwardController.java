/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Award;
import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.AwardService;
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
@RequestMapping("award")
public class AwardController {
    
    @Autowired
    private AwardService service;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("")
    public String awardPage(){
        return "employee-awards";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Award> getAll(){
        return service.getAllAward().getData();
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Award> insert(@RequestBody Award award){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        award.setEmployee(employee);
        return service.insert(award);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<Award> update(@PathVariable Integer id,@RequestBody Award award){
        return service.update(id,award);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<Award> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
