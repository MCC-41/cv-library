/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.modals.data;

/**
 *
 * @author Adhi
 * @param <E>
 */
public class ResponseData<E> {
    private E data;

    public ResponseData() {
    }

    public ResponseData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
    
}
