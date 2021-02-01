/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.models.data;

import java.util.List;

/**
 *
 * @author Adhi
 */
public class ResponseList<E> {
    private List<E> data;

    public ResponseList() {
    }

    public ResponseList(List<E> data) {
        this.data = data;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
    
}
