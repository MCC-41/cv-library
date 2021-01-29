/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Adhi
 */

public class Work {
    
    private Integer id;
    private String companyName;
    private String position;
    private String jobDesc;
    private Date startDate;
    private Date endDate;
    private Employee employee;

    public Work() {
    }

    public Work(Integer id, String companyName, String position, String jobDesc, Date startDate, Date endDate, Employee employee) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
        
    
    
    
}
