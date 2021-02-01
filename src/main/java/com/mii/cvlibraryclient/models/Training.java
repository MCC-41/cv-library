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
public class Training {
    
    private Integer id;
    private String name;
    private String institution;
    private String year;
    private Employee employee;
    private TrainingType trainingType;
    private byte[] file;

    public Training() {
    }

    public Training(Integer id, String name, String institution, String year, Employee employee, TrainingType trainingType, byte[] file) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.year = year;
        this.employee = employee;
        this.trainingType = trainingType;
        this.file = file;
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

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
}
