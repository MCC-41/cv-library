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

public class Organization {
    
    private Integer id;
    private String name;
    private String institution;
    private String startDate;
    private String endStart;
    private Employee employee;

    public Organization() {
    }

    public Organization(Integer id, String name, String institution, String startDate, String endStart, Employee employee) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.startDate = startDate;
        this.endStart = endStart;
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

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndStart() {
        return endStart;
    }

    public void setEndStart(String endStart) {
        this.endStart = endStart;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
}
