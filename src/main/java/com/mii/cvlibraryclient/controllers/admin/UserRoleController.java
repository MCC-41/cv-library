/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.controllers.UserController;
import com.mii.cvlibraryclient.models.UserRole;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
import com.mii.cvlibraryclient.services.RoleService;
import com.mii.cvlibraryclient.services.UserRoleService;
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
@RequestMapping("user_role")
@Controller
public class UserRoleController {
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping("")
    public String page(Model model){
        
        return "admin-role";
    }
    
    @GetMapping("/all")
    public @ResponseBody List<UserRole> getAll(){
        return userRoleService.getAll().getData();
    }
    
    @PostMapping("")
    public @ResponseBody ResponseMessage<UserRole> insert(@RequestBody UserRole userRole){
        return userRoleService.insert(userRole);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<UserRole> update(@PathVariable Integer id, @RequestBody UserRole userRole){
        return userRoleService.update(id, userRole);
    }
    
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseMessage<UserRole> delete(@PathVariable Integer id){
        return userRoleService.delete(id);
    }
}
