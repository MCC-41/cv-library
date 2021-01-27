/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Training;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.TrainingService;
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
public class TrainingController implements IController<Training, Integer>{

    @Autowired
    private TrainingService ts;
    
    @GetMapping("training")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<Training> getAll() {
        return new ResponseList(ts.getAll());
    }

    @GetMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<Training> getById(Integer id) {
        return ResponseRest.success(ts.getById(id));
    }

    @PostMapping("training")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<Training> insert(Training data) {
        try {
            return ResponseRest.success(ts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<Training> update(Integer id, Training data) {
        try {
            return ResponseRest.success(ts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("training/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<Training> delete(Integer id) {
        if(ts.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
