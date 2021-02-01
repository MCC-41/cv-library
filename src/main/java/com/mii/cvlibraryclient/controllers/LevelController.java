/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Level;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("level")
public class LevelController {
    
    @Autowired
    private LevelService service;
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("titlePage","Level");
        model.addAttribute("levels",service.getAll());
        System.out.println(service.getAll());
        return "admin-edu";
            
    }
    
    @GetMapping("add")
    public String create(Model model){
        model.addAttribute("titlePage", "Level insert");
        return "create-level";
    }
    
    @PostMapping("add")
    public String postCreate(Level level){
        service.postLevel(level);
        return "redirect:/level";
        
    }
    
    @GetMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage","Level edit");
        model.addAttribute("levels", service.getLevelById(id).getData());
        return "update-level";
    }
    
    @PostMapping("/{id}/edit")
    public String putUpdate(@PathVariable Integer id, Level level){
        service.putLevel(level);
        return "redirect:/level";
    }
    
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        ResponseMessage<Level> message = service.deleteLevel(id);
        System.out.println(message.getMessage());
        return "redirect:/level";
    }
    
    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Integer id, Model model){
        model.addAttribute("titlePage", "Level detail");
        model.addAttribute("levels", service.getLevelById(id).getData());
        return "detail-level";
    }
    
    
}
