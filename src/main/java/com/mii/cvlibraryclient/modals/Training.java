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
@Data
public class Training {
    
    private Integer id;
    private String name;
    private String institusion;
    private Date year;
    private Employee employee;
    private TrainingType trainingType;

    public Training() {
    }

    public Training(Integer id, String name, String institusion, Date year, Employee employee, TrainingType trainingType) {
        this.id = id;
        this.name = name;
        this.institusion = institusion;
        this.year = year;
        this.employee = employee;
        this.trainingType = trainingType;
    }
    
    
    
}
