/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models.auth;

import java.util.List;
import lombok.Data;

/**
 *
 * @author habib
 */
@Data
public class AuthResponse {
    private String username;
    private List<String> authority;

    public AuthResponse() {
    }

    public AuthResponse(String username, List<String> authority) {
        this.username = username;
        this.authority = authority;
    }
    
}
