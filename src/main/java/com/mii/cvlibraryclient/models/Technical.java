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

public class Technical {
    
    private Integer id;
    private String name;
    private Employee employee;
    private TechnicalType technicalType;

    public Technical() {
    }

    public Technical(Integer id, String name, Employee employee, TechnicalType technicalType) {
        this.id = id;
        this.name = name;
        this.employee = employee;
        this.technicalType = technicalType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TechnicalType getTechnicalType() {
        return technicalType;
    }

    public void setTechnicalType(TechnicalType technicalType) {
        this.technicalType = technicalType;
    }
    
}
