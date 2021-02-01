/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models;

import java.util.Date;

/**
 *
 * @author Adhi
 */

public class Work {
    
    private Integer id;
    private String companyName;
    private String position;
    private String jobDesc;
    private String startDate;
    private String endDate;
    private Employee employee;

    public Work() {
    }

    public Work(Integer id, String companyName, String position, String jobDesc, String startDate, String endDate, Employee employee) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.jobDesc = jobDesc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
        
    
    
    
}
