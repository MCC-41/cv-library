/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals;

import lombok.Data;

/**
 *
 * @author Adhi
 */

@Data
public class Technical {
    
    private Integer id;
    private Integer name;
    private Employee employee;
    private TechnicalType technicalType;

    public Technical() {
    }

    public Technical(Integer id, Integer name, Employee employee, TechnicalType technicalType) {
        this.id = id;
        this.name = name;
        this.employee = employee;
        this.technicalType = technicalType;
    }
    
    
    
    
}
