/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.Role;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;

import com.mii.cvlibraryclient.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */
@RequestMapping("role")
@Controller
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping("")
    @ResponseBody 
    public List<Role> role(){
        List<Role> role = roleService.getAll().getData();
        return role;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<Role> getById(@PathVariable Integer id){
        ResponseData<Role> role = roleService.getById(id);
        return role;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<Role> insert(@RequestBody Role role){
        return roleService.insert(role);
    }
    
    @PutMapping("{id}")
    @ResponseBody
    public ResponseMessage<Role> update(@PathVariable Integer id,@RequestBody Role role){
        return roleService.update(id,role);
    }
    
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage<Role> delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
    
            
}
