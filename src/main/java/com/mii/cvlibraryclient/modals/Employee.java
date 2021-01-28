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
public class Employee {
    
    private Integer id;
    private String email;
    private String name;
    private Date dateBirth;
    private String nation;
    private String status;
    private String gender;
    private Religion idReligion;

    public Employee() {
    }

    public Employee(Integer id, String email, String name, Date dateBirth, String nation, String status, String gender, Religion idReligion) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.dateBirth = dateBirth;
        this.nation = nation;
        this.status = status;
        this.gender = gender;
        this.idReligion = idReligion;
    }
    
    
    
    
    
}
