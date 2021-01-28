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
public class TechnicalType {
    
    private Integer id;
    private String name;

    public TechnicalType() {
    }

    public TechnicalType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    
    
    
}
