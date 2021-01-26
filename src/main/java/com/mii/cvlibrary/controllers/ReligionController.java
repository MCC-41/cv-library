/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Religion;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.ReligionService;
import javafx.scene.layout.Region;
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
public class ReligionController implements IController<Religion, Integer>{

    @Autowired
    private ReligionService rs;
    
    @GetMapping("religion")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseList<Religion> getAll() {
        return new ResponseList(rs.getAll());
    }

    @GetMapping("religion/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseRest<Religion> getById(Integer id) {
        return ResponseRest.success(rs.getById(id));
    }

    @PostMapping("religion")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    @Override
    public ResponseRest<Religion> insert(Religion data) {
        try {
            return ResponseRest.success(rs.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("religion/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    @Override
    public ResponseRest<Religion> update(Integer id, Religion data) {
        try {
            return ResponseRest.success(rs.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("religion/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    @Override
    public ResponseRest<Religion> delete(Integer id) {
        if(rs.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
