/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.services.iservices;

import java.util.List;

/**
 *
 * @author habib
 */
public interface IService<T,I> {
    List<T> getAll();
    T getById(I id);
    T insert(T data);
    T update(I id,T data);
    boolean delete(I id);
}
