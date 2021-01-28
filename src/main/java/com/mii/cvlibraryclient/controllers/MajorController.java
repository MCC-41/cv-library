/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.modals.Major;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LevelService;
import com.mii.cvlibraryclient.services.MajorService;
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
@RequestMapping("major")
public class MajorController {
    
    @Autowired
    private MajorService service;
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("titlePage","Major");
        model.addAttribute("majors",service.getAll());
        return "admin-edu";
            
    }
    
    @GetMapping("add")
    public String create(Model model){
        model.addAttribute("titlePage", "Level insert");
        return "create-major";
    }
    
    @PostMapping("add")
    public String postCreate(Major major){
        service.postMajor(major);
        return "redirect:/major";
        
    }
    
    @GetMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage","Major edit");
        model.addAttribute("majors", service.getMajorById(id));
        return "update-major";
    }
    
    @PostMapping("/{id}/edit")
    public String putUpdate(@PathVariable Integer id, Major major){
        service.putMajor(major);
        return "redirect:/major";
    }
    
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        ResponseMessage<Major> message = service.deleteMajor(id);
        System.out.println(message.getMessage());
        return "redirect:/major";
    }
    
    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage", "Major detail");
        model.addAttribute("majors", service.getMajorById(id).getData());
        return "detail-major";
    }
    
}
