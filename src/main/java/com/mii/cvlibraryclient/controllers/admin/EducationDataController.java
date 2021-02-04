/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.services.LevelService;
import com.mii.cvlibraryclient.services.MajorService;
import com.mii.cvlibraryclient.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("education-master")
public class EducationDataController {
    @Autowired
    private LevelService service;
    @Autowired
    private MajorService serviceMajor;
    @Autowired
    private UniversityService serviceUniversity;
    
    @GetMapping("")
    public String adminEdu(Model model) {
        model.addAttribute("levels",service.getAll().getData());
        model.addAttribute("majors",serviceMajor.getAll().getData());
        model.addAttribute("universities",serviceUniversity.getAll().getData());
        return "admin-edu";
    }
}
