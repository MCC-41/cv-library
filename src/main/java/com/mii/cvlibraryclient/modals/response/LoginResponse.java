/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals.response;

/**
 *
 * @author Adhi
 */
public class LoginResponse {
    private LoginDataResponse data;
    private boolean success;
    private String message;

    public LoginDataResponse getData() {
        return data;
    }
    
    public void setData(LoginDataResponse data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
