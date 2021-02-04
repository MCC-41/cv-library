/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models;


/**
 *
 * @author Adhi
 */

public class UserRole {
    
    private Integer id;
    private User user;
    private Role role;

    public UserRole() {
    }

    public UserRole(Integer id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    
}
