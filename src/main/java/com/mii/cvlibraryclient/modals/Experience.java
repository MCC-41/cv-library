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

public class Experience {
    
    private Integer id;
    private String name;
    private Date year;
    private Employee employee;

    public Experience() {
    }

    public Experience(Integer id, String name, Date year, Employee employee) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.employee = employee;
    }
    
    
    
    
}
