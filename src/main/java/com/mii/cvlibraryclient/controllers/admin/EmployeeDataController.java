/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Religion;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.EmployeeService;
import com.mii.cvlibraryclient.services.ReligionService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author habib
 */
@Controller
@RequestMapping("employee-master")
@PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
public class EmployeeDataController {

    @Autowired
    private EmployeeService service;
    @Autowired
    private ReligionService religionService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public String adminEdu(Model model) {
        model.addAttribute("religions", religionService.getAll().getData());
        return "admin-employee";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    public @ResponseBody
    List<Employee> getAll() {
        return service.getAll().getData();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    public @ResponseBody
    ResponseMessage<Employee> insert(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateBirth,
            @RequestParam String nation,
            @RequestParam String status,
            @RequestParam String gender,
            @RequestParam Integer religion,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        Employee employee = new Employee();
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
            return service.insert(employee, file);
        }else{
            return service.insertWithoutFile(employee);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public @ResponseBody
    ResponseMessage<Employee> update(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateBirth,
            @RequestParam String nation,
            @RequestParam String status,
            @RequestParam String gender,
            @RequestParam Integer religion,
            @RequestParam(value = "file", required = false) MultipartFile file){
        Employee employee = new Employee();
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    public @ResponseBody
    ResponseMessage<Employee> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
