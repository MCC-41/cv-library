/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.modals.University;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
import com.mii.cvlibraryclient.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adhi
 */
@Controller
@RequestMapping("university")
public class UniversityController {
    
    @Autowired
    private UniversityService service;
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("titlePage","University");
        model.addAttribute("universities",service.getAll());
        return "admin-edu";
            
    }
    
    @GetMapping("add")
    public String create(Model model){
        model.addAttribute("titlePage", "University insert");
        return "create-university";
    }
    
    @PostMapping("add")
    public String postCreate(University university){
        service.postUniversity(university);
        return "redirect:/university";
        
    }
    
    @GetMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage","Level edit");
        model.addAttribute("levels", service.getUniversityById(id).getData());
        return "update-university";
    }
    
    @PostMapping("/{id}/edit")
    public String putUpdate(@PathVariable Integer id, University university){
        service.putUniversity(university);
        return "redirect:/university";
    }
    
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        ResponseMessage<University> message = service.deleteUniversity(id);
        System.out.println(message.getMessage());
        return "redirect:/university";
    }
    
    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage", "Level detail");
        model.addAttribute("levels", service.getUniversityById(id));
        return "detail-university";
    }
    
}
