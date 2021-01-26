/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Permission;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.PermissionService;
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
public class PermissionController implements IController<Permission, Integer> {

    @Autowired
    private PermissionService ps;
    
    @GetMapping("permission")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseList<Permission> getAll() {
        return new ResponseList(ps.getAll());
    }

    @GetMapping("permission/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseRest<Permission> getById(Integer id) {
        return ResponseRest.success(ps.getById(id));
    }

    @PostMapping("permission")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    @Override
    public ResponseRest<Permission> insert(Permission data) {
        try {
            return ResponseRest.success(ps.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("permission/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    @Override
    public ResponseRest<Permission> update(Integer id, Permission data) {
        try {
            return ResponseRest.success(ps.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("permission/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    @Override
    public ResponseRest<Permission> delete(Integer id) {
        if(ps.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
