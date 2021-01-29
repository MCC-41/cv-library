/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals.auth;

import java.util.List;

/**
 *
 * @author Adhi
 */
public class AuthResponse {
    
    private String username;
    private List<String> authority;

    public AuthResponse() {
    }

    public AuthResponse(String username, List<String> authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public void setAuthority(List<String> authority) {
        this.authority = authority;
    }
    
}
