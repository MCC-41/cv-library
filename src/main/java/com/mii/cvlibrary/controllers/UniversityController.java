/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.University;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UniversityController implements IController<University, Integer>{

    @Autowired
    private UniversityService us;
    
    @GetMapping("university")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseList<University> getAll() {
        return new ResponseList(us.getAll());
    }

    @GetMapping("university/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseRest<University> getById(Integer id) {
        return ResponseRest.success(us.getById(id));
    }

    @PostMapping("university")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    @Override
    public ResponseRest<University> insert(University data) {
        try {
            return ResponseRest.success(us.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("university/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    @Override
    public ResponseRest<University> update(Integer id, University data) {
        try {
            return ResponseRest.success(us.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("university/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    @Override
    public ResponseRest<University> delete(Integer id) {
        if(us.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
