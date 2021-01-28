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
public class Organization {
    
    private Integer id;
    private String name;
    private String institusion;
    private Date startDate;
    private Date endStart;
    private Employee employee;

    public Organization() {
    }

    public Organization(Integer id, String name, String institusion, Date startDate, Date endStart, Employee employee) {
        this.id = id;
        this.name = name;
        this.institusion = institusion;
        this.startDate = startDate;
        this.endStart = endStart;
        this.employee = employee;
    }
    
    
    
    
    
}
