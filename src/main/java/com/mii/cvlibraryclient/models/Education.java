/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Adhi
 */
public class Education {
    
    private Integer id;
    private String year;
    private String ipk;
    private University university;
    private Major major;
    private Level level;
    private Employee employee;

    public Education() {
    }

    public Education(Integer id, String year, String ipk, University university, Major major, Level level, Employee employee) {
        this.id = id;
        this.year = year;
        this.ipk = ipk;
        this.university = university;
        this.major = major;
        this.level = level;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
