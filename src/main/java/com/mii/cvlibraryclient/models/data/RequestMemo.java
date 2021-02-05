/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models.data;

/**
 *
 * @author habib
 */
public class RequestMemo {
    private String email;
    private String memo;

    public RequestMemo() {
    }

    public RequestMemo(String email, String memo) {
        this.email = email;
        this.memo = memo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
}
