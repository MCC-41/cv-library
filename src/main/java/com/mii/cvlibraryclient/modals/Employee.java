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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Religion getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Religion idReligion) {
        this.idReligion = idReligion;
    }
    
    
    
    
    
}
