/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.UserPermission;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.PermissionService;
import com.mii.cvlibraryclient.services.UserPermissionService;
import com.mii.cvlibraryclient.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("user_permission")
@Controller
public class UserPermissionController {
    
    @Autowired
    private UserPermissionService userPermissionService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private PermissionService permissionService;
    
    @GetMapping("")
    public String page(Model model){
        model.addAttribute("permissions", permissionService.getAll().getData());
        model.addAttribute("users" , userService.getAll().getData());
        return "admin-role";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<UserPermission> getAll(){
        return userPermissionService.getAll().getData();
    }
    
    @PostMapping("")
    public @ResponseBody ResponseMessage<UserPermission> insert(@RequestBody UserPermission userPermission){
        return userPermissionService.insert(userPermission);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<UserPermission> update(@PathVariable Integer id, @RequestBody UserPermission userPermission){
        return userPermissionService.update(id, userPermission);
    }
    
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<UserPermission> delete(@PathVariable Integer id){
        return userPermissionService.delete(id);
    }

    
}
