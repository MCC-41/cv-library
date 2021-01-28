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
public class TrainingType {
    
    private Integer id;
    private String name;

    public TrainingType() {
    }

    public TrainingType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    
    
    
}
