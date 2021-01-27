/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models.data;

import lombok.Data;

/**
 *
 * @author habib
 */
@Data
public class MemoRequest {
    private String email;
    private String memo;

    public MemoRequest() {
    }

    public MemoRequest(String email, String memo) {
        this.email = email;
        this.memo = memo;
    }
    
}
