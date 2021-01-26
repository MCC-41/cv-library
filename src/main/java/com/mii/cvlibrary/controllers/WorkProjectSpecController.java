/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.WorkProjectSpec;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.WorkProjectSpecService;
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
public class WorkProjectSpecController implements IController<WorkProjectSpec, Integer>{

    @Autowired
    private WorkProjectSpecService wpss;
    
    @GetMapping("project_spec")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseList<WorkProjectSpec> getAll() {
        return new ResponseList(wpss.getAll());
    }

    @GetMapping("project_spec/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseRest<WorkProjectSpec> getById(Integer id) {
        return ResponseRest.success(wpss.getById(id));
    }

    @PostMapping("project_spec")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    @Override
    public ResponseRest<WorkProjectSpec> insert(WorkProjectSpec data) {
        try {
            return ResponseRest.success(wpss.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("project_spec/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    @Override
    public ResponseRest<WorkProjectSpec> update(Integer id, WorkProjectSpec data) {
        try {
            return ResponseRest.success(wpss.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("project_spec/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    @Override
    public ResponseRest<WorkProjectSpec> delete(Integer id) {
        if(wpss.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
