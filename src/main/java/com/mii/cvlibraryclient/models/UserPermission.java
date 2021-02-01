/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models;

import lombok.Data;

/**
 *
 * @author Adhi
 */
@Data
public class UserPermission {
    
    private Integer id;
    private User user;
    private Permission permission;

    public UserPermission() {
    }

    public UserPermission(Integer id, User user, Permission permission) {
        this.id = id;
        this.user = user;
        this.permission = permission;
    }
    
    
    
}
