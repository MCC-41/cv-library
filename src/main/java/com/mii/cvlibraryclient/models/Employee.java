/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models;

/**
 *
 * @author Adhi
 */
public class Employee {
    
    private Integer id;
    private String email;
    private String name;
    private String dateBirth;
    private String nation;
    private String status;
    private String gender;
    private Religion religion;

    public Employee() {
    }

    public Employee(Integer id, String email, String name, String dateBirth, String nation, String status, String gender, Religion religion) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.dateBirth = dateBirth;
        this.nation = nation;
        this.status = status;
        this.gender = gender;
        this.religion = religion;
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

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
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

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

   
}
