/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.Permission;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.PermissionService;
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

@RequestMapping("permission")
@Controller
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    @GetMapping("")
    @ResponseBody 
    public List<Permission> permission(){
        List<Permission> permission = permissionService.getAll().getData();
        return permission;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<Permission> getById(@PathVariable Integer id){
        ResponseData<Permission> permission = permissionService.getById(id);
        return permission;
        
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<Permission> insert(@RequestBody Permission permission){
        return permissionService.insert(permission);
    }
    
    @PutMapping("{id}")
    @ResponseBody
    public ResponseMessage<Permission> update(@PathVariable Integer id, @RequestBody Permission permission ){
        return permissionService.update(id,permission);
    }
    
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage<Permission> delete(@PathVariable Integer id){
        return permissionService.delete(id);
    }
            
    
    
    
    
    
    
    
    
    
    
}
