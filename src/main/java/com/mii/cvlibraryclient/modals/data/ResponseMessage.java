/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals.data;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Adhi
 * @param <E>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<E> extends ResponseData<E> {
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, E data) {
        super(data);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
