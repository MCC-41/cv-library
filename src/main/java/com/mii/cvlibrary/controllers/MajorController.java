/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Major;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.MajorService;
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
public class MajorController implements IController<Major, Integer>{

    @Autowired
    private MajorService ms;
    
    @GetMapping("major")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<Major> getAll() {
        return new ResponseList(ms.getAll());
    }

    @GetMapping("major/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<Major> getById(Integer id) {
        return ResponseRest.success(ms.getById(id));
    }

    @PostMapping("major")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<Major> insert(Major data) {
        try {
            return ResponseRest.success(ms.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("major/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<Major> update(Integer id, Major data) {
        try {
            return ResponseRest.success(ms.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("major/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<Major> delete(Integer id) {
        if(ms.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
