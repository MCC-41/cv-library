/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals;

import lombok.Data;

/**
 *
 * @author Adhi
 */

@Data
public class User {
    
    private Integer id;
    private String username;
    private String password;
    private boolean isVerified;
    private Employee employee;
    private Status status;

    public User() {
    }

    public User(Integer id, String username, String password, boolean isVerified, Employee employee, Status status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isVerified = isVerified;
        this.employee = employee;
        this.status = status;
    }
    
    
    
}
