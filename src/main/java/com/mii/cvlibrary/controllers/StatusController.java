/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Status;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.StatusService;
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
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class StatusController implements IController<Status, Integer>{

    @Autowired
    private StatusService ss;
    
    @GetMapping("status")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<Status> getAll() {
        return new ResponseList(ss.getAll());
    }

    @GetMapping("status/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<Status> getById(Integer id) {
        return ResponseRest.success(ss.getById(id));
    }

    @PostMapping("status")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<Status> insert(Status data) {
        try {
            return ResponseRest.success(ss.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("status/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<Status> update(Integer id, Status data) {
        try {
            return ResponseRest.success(ss.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("status/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<Status> delete(Integer id) {
        if(ss.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
