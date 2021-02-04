/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.configs.PasswordConfig;
import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Employee;
import com.mii.cvlibrary.models.Status;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.EmployeeService;
import com.mii.cvlibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private EmployeeService es;
    @Autowired
    private PasswordConfig passwordConfig;
    
    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseList<User> getAll() {
        return new ResponseList(us.getAll());
    }

    @GetMapping("user/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @Override
    public ResponseRest<User> getById(Integer id) {
        return ResponseRest.success(us.getById(id));
    }
    
    @GetMapping("user/{username}/id")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public Integer getByUsername(@PathVariable String username) {
        return us.getByUsername(username).getId();
    }

    @PostMapping("user")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN','CREATE_USER')")
    @Override
    public ResponseRest<User> insert( User data) {
        try {
            Employee employee = es.getById(data.getId());
            data.setUsername(makeUsername(employee.getName()));
//            String[] split = employee.getDateBirth().toString().split("-");
//            System.out.println(split[0]);
//            System.out.println(split[1]);
//            System.out.println(split[2]);
            data.setPassword(passwordConfig.passwordEncoder().encode(makePassword(employee.getDateBirth().toString())));
//            data.setStatus(new Status(0));
//            data.setVerified(true);
//            Employee e = new Employee();
//            e.setId(data.getId());
//            data.setEmployee(e);
            return ResponseRest.success(us.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed"+e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("user/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    @Override
    public ResponseRest<User> update(Integer id, User data) {
        try {
            return ResponseRest.success(us.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("user/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN','DELETE_USER')")
    @Override
    public ResponseRest<User> delete(Integer id) {
        if(us.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("user/{id}/password")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN','UPDATE_USER')")
    public ResponseRest<User> updatePassword(Integer id, User data) {
        try {
            data.setPassword(passwordConfig.passwordEncoder().encode(data.getPassword()));
            return ResponseRest.success(us.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    private String makeUsername(String name){
        String arr[] = name.split(" ");
        if(arr.length>1){
            String firstWord = arr[0];
            String theRest = arr[arr.length-1];
            return firstWord+"."+theRest;
        }else{
            return name;
        }
    }
    private String makePassword(String date){
        String arr[] = date.split("-");
        return arr[2]+arr[1]+arr[0].substring(2,4);
    }
}
