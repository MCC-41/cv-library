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
public class Experience {
    
    private Integer id;
    private String name;
    private String year;
    private Employee employee;

    public Experience() {
    }

    public Experience(Integer id, String name, String year, Employee employee) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.employee = employee;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
