/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.TrainingType;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.TrainingTypeService;
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
public class TrainingTypeController implements IController<TrainingType, Integer>{
    @Autowired
    private TrainingTypeService tts;
    
    @GetMapping("trainingtype")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseList<TrainingType> getAll() {
        return new ResponseList(tts.getAll());
    }

    @GetMapping("trainingtype/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    @Override
    public ResponseRest<TrainingType> getById(Integer id) {
        return ResponseRest.success(tts.getById(id));
    }

    @PostMapping("trainingtype")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN')")
    @Override
    public ResponseRest<TrainingType> insert(TrainingType data) {
        try {
            return ResponseRest.success(tts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("trainingtype/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    @Override
    public ResponseRest<TrainingType> update(Integer id, TrainingType data) {
        try {
            return ResponseRest.success(tts.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("trainingtype/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    @Override
    public ResponseRest<TrainingType> delete(Integer id) {
        if(tts.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
