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
public class Work {
    
    private Integer id;
    private String companyName;
    private String position;
    private String jobDesc;
    private Date startDate;
    private Date endDate;
    private Employee employee;

    public Work() {
    }

    public Work(Integer id, String companyName, String position, String jobDesc, Date startDate, Date endDate, Employee employee) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.jobDesc = jobDesc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }
    
    
    
    
}
