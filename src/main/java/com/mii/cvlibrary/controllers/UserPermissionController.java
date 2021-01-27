/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.UserPermission;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class UserPermissionController implements IController<UserPermission, Integer>{

    @Autowired
    private UserPermissionService ups;
    
    @Override
    public ResponseList<UserPermission> getAll() {
        return new ResponseList(ups.getAll());
    }

    @Override
    public ResponseRest<UserPermission> getById(Integer id) {
        return ResponseRest.success(ups.getById(id));
    }

    @Override
    public ResponseRest<UserPermission> insert(UserPermission data) {
        try {
            return ResponseRest.success(ups.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<UserPermission> update(Integer id, UserPermission data) {
        try {
            return ResponseRest.success(ups.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<UserPermission> delete(Integer id) {
        if(ups.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
