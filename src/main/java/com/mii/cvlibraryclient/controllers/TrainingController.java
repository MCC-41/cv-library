/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Training;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.TrainingService;
import com.mii.cvlibraryclient.services.TrainingTypeService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("training")
public class TrainingController {

    @Autowired
    private TrainingService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TrainingTypeService typeService;

    @GetMapping("")
    public String page(Model model) {
        model.addAttribute("types", typeService.getAll().getData());
        return "employee-training";
    }

    @GetMapping("/all")
    public @ResponseBody
    List<Training> getAll() {
        return service.getAllTraining().getData();
    }

    @PostMapping("/add")
    public @ResponseBody
    ResponseMessage<Training> insert(@RequestBody Training training,@RequestBody MultipartFile file) throws IOException {
        Employee employee = new Employee();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        employee.setId(loginService.getIdEmployee());
        training.setEmployee(employee);
        System.out.println(Arrays.toString(file.getBytes()));
        training.setFile(file.getBytes());
        return service.insert(training);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseMessage<Training> update(@PathVariable Integer id, @RequestBody Training training) {
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        training.setEmployee(employee);
        return service.update(id, training);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseMessage<Training> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
