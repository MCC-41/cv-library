/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.UserService;
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
public class UserController implements IController<User, Integer>{

    @Autowired
    private UserService us;
    
    @GetMapping("level")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<User> getAll() {
        return new ResponseList(us.getAll());
    }

    @GetMapping("level/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<User> getById(Integer id) {
        return ResponseRest.success(us.getById(id));
    }

    @PostMapping("level")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<User> insert(User data) {
        try {
            return ResponseRest.success(us.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("level/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<User> update(Integer id, User data) {
        try {
            return ResponseRest.success(us.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("level/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<User> delete(Integer id) {
        if(us.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
