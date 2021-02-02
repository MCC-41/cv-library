/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Work;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.WorkService;
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
@RequestMapping("work")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class WorkController {
    
    @Autowired
    private WorkService service;
    @Autowired
    private LoginService loginService;
    
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String workPage(Model model){
        model.addAttribute("works",service.getAll().getData());
        return "employee-work";
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody List<Work> getAll(){
        return service.getAllWork().getData();
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public @ResponseBody ResponseMessage<Work> insert(@RequestBody Work work){
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        work.setEmployee(employee);
        return service.postWork(work);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public @ResponseBody ResponseMessage<Work> update(@PathVariable String id,@RequestBody Work work){
        work.setId(Integer.parseInt(id));
        return service.putWork(Integer.parseInt(id),work);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    public @ResponseBody ResponseMessage<Work> delete(@PathVariable Integer id){
        return service.deleteWork(id);
    }
}
