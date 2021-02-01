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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
