/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.services.AwardService;
import com.mii.cvlibraryclient.services.EducationService;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.ExperienceService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.OrganizationService;
import com.mii.cvlibraryclient.services.TechnicalService;
import com.mii.cvlibraryclient.services.TrainingService;
import com.mii.cvlibraryclient.services.WorkService;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("detail")
public class EmployeeDetailController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private TechnicalService technicalService;
    @Autowired
    private WorkService workService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping("")
    public String detail(Model model) throws IOException{
        model.addAttribute("employees", service.getById(loginService.getIdEmployee()).getData());
        model.addAttribute("educations", educationService.getAllEducation().getData());
        model.addAttribute("technicals", technicalService.getAllTechnical().getData());
        model.addAttribute("works", workService.getAllWork().getData());
        model.addAttribute("trainings", trainingService.getAllTraining().getData());
        model.addAttribute("organizations", organizationService.getAllOrganization().getData());
        model.addAttribute("experiences", experienceService.getAllExperience().getData());
        model.addAttribute("awards", awardService.getAllAward().getData());
        model.addAttribute("foto", service.getdown(loginService.getIdEmployee()));
//        System.out.println(awardService.getAllAward().getData().isEmpty());
        return "employee-detail";
    }
}
