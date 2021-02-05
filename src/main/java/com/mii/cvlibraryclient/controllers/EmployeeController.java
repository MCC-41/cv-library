/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Religion;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.ReligionService;
import java.net.URL;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Adhi
 */
@Controller
@RequestMapping("employee")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ReligionService religionService;
    
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String page(Model model) {
        model.addAttribute("religions", religionService.getAll().getData());
        model.addAttribute("employees", service.getById(loginService.getIdEmployee()).getData());
        return "employee";
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody
    List<Employee> getAll() {
        return service.getAll().getData();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public @ResponseBody
    ResponseData<Employee> getAll(@PathVariable Integer id) {
        return service.getById(id);
    }

//    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
//    public @ResponseBody
//    ResponseMessage<Employee> insert(@RequestBody Employee employee) {
////        Employee employee = new Employee();
////        employee.setId(loginService.getIdEmployee());
////        employee.setEmployee(employee);
//        return service.insert(employee);
//    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
//    public @ResponseBody ResponseMessage<Employee> update(@PathVariable Integer id,@RequestBody Employee employee){
//        return service.update(id,employee);
//    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    public @ResponseBody
    ResponseMessage<Employee> delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public @ResponseBody
    ResponseMessage<Employee> update2(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateBirth,
            @RequestParam String nation,
            @RequestParam String status,
            @RequestParam String gender,
            @RequestParam Integer religion,
            @RequestParam(value = "file",required = false) MultipartFile file) {
        Employee employee = service.getById(id).getData();
        employee.setName(name);
        employee.setEmail(email);
        employee.setDateBirth(dateBirth);
        employee.setNation(nation);
        employee.setStatus(status);
        employee.setGender(gender);
        Religion r = new Religion();
        r.setId(religion);
        employee.setReligion(r);
        if(file!=null){
            return service.update(id, employee, file);
        }else{
            return service.updateWithoutFile(id, employee);
        }
    }
    
//    @GetMapping("/{id}/photo")
//    public ByteArrayResource getPhoto(@PathVariable Integer id) {
//        Employee employee = service.getById(id).getData();
//        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\employee-photo\\"+employee.getPhoto();
//        return service.getdown(id);
//    }
    
    @GetMapping("/photo")
    public @ResponseBody ResponseEntity getDown() {
        try {
            ByteArrayResource data = service.getdown(loginService.getIdEmployee());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(data);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("{id}/photo")
    public @ResponseBody ResponseEntity getPhoto(@PathVariable Integer id) {
        try {
            ByteArrayResource data = service.getdown(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(data);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }
}