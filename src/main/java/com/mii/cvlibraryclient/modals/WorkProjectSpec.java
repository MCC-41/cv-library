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
public class WorkProjectSpec {
    
    private Integer id;
    private String name;
    private Work work;

    public WorkProjectSpec() {
    }

    public WorkProjectSpec(Integer id, String name, Work work) {
        this.id = id;
        this.name = name;
        this.work = work;
    }
    
    
    
}
