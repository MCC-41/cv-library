/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.TechnicalType;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.TechnicalTypeService;
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
public class TechnicalTypeController implements IController<TechnicalType, Integer>{

    @Autowired
    private TechnicalTypeService service;
    
    @GetMapping("technical_type")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<TechnicalType> getAll() {
        return new ResponseList(service.getAll());
    }

    @GetMapping("technical_type/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<TechnicalType> getById(Integer id) {
        return ResponseRest.success(service.getById(id));
    }

    @PostMapping("technical_type")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<TechnicalType> insert(TechnicalType data) {
        try {
            return ResponseRest.success(service.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("technical_type/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<TechnicalType> update(Integer id, TechnicalType data) {
        try {
            return ResponseRest.success(service.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("technical_type/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<TechnicalType> delete(Integer id) {
        if(service.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
