/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.modals.Work;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
import com.mii.cvlibraryclient.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */

@Controller
@RequestMapping("work")
public class WorkController {
    
    @Autowired
    private WorkService service;
    
    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("works",service.getAll().getData());
        
        return "employee-work";
    }
    
    @PostMapping("/add")
    public @ResponseBody ResponseMessage<Work> insert(@RequestBody Work work){
        return service.postWork(work);
    }
    
    
    
        
    
}
