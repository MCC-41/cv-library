/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Training;
import com.mii.cvlibraryclient.models.TrainingType;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.TrainingService;
import com.mii.cvlibraryclient.services.TrainingTypeService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("training")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class TrainingController {

    @Autowired
    private TrainingService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TrainingTypeService typeService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String page(Model model) {
        model.addAttribute("types", typeService.getAll().getData());
        return "employee-training";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody
    List<Training> getAll() {
        return service.getAllTraining().getData();
    }
    
    @GetMapping("/download")
    public @ResponseBody ResponseEntity getDown() {
        try {
            ByteArrayResource data = service.getdown();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + "Muhamad_Habib_Abdul_Aziz.pdf" + "\"")
                    .contentLength(data.contentLength())
                    .body(data);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public @ResponseBody
    ResponseMessage<Training> insert(
            @RequestParam String name,
            @RequestParam String institution,
            @RequestParam String year,
            @RequestParam Integer trainingType,
            @RequestParam MultipartFile file) throws IOException {
        
        Training training = new Training();
        training.setName(name);
        training.setInstitution(institution);
        training.setYear(year);
        TrainingType type = new TrainingType();
        type.setId(trainingType);
        training.setTrainingType(type);
        Employee emp = new Employee();
        emp.setId(loginService.getIdEmployee());
        training.setEmployee(emp);
        return service.insert(training,file);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public @ResponseBody
    ResponseMessage<Training> update(
            @PathVariable Integer id, 
            @RequestParam String name,
            @RequestParam String institution,
            @RequestParam String year,
            @RequestParam Integer trainingType,
            @RequestParam MultipartFile file) throws IOException {
        Training training = new Training();
        training.setName(name);
        training.setInstitution(institution);
        training.setYear(year);
        TrainingType type = new TrainingType();
        type.setId(trainingType);
        training.setTrainingType(type);
        Employee employee = new Employee();
        employee.setId(loginService.getIdEmployee());
        training.setEmployee(employee);
        return service.update(id, training, file);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    public @ResponseBody
    ResponseMessage<Training> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
