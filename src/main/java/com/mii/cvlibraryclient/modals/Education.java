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
public class Education {
    
    private Integer id;
    private Date year;
    private String ipk;
    private University university;
    private Major major;
    private Level level;
    private Employee employee;

    public Education() {
    }

    public Education(Integer id, Date year, String ipk, University university, Major major, Level level, Employee employee) {
        this.id = id;
        this.year = year;
        this.ipk = ipk;
        this.university = university;
        this.major = major;
        this.level = level;
        this.employee = employee;
    }
    
    
    
    
}
