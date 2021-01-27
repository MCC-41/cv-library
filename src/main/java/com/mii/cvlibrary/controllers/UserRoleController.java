/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.UserRole;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class UserRoleController implements IController<UserRole, Integer>{

    @Autowired
    private UserRoleService urs;
    
    @Override
    public ResponseList<UserRole> getAll() {
        return new ResponseList(urs.getAll());
    }

    @Override
    public ResponseRest<UserRole> getById(Integer id) {
        return ResponseRest.success(urs.getById(id));
    }

    @Override
    public ResponseRest<UserRole> insert(UserRole data) {
        try {
            return ResponseRest.success(urs.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<UserRole> update(Integer id, UserRole data) {
        try {
            return ResponseRest.success(urs.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<UserRole> delete(Integer id) {
        if(urs.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
