/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.models.data;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author habib
 */
public class ResponseRest<E> extends ResponseEntity<ResponseMessage<E>>{

    public ResponseRest(ResponseMessage<E> t, HttpStatus hs) {
        super(t, hs);
    }
    
    public static <E>ResponseRest<E> success(E data,String message){
        return new ResponseRest<>(new ResponseMessage(message,data),HttpStatus.OK);
    }
    public static <E>ResponseRest<E> success(String message){
        return new ResponseRest<>(new ResponseMessage(message,null),HttpStatus.OK);
    }
    public static <E>ResponseRest<E> success(E data){
        return new ResponseRest<>(new ResponseMessage(null,data),HttpStatus.OK);
    }
//    
    public static <E>ResponseRest<E> failed(String message,HttpStatus status){
        return new ResponseRest<>(new ResponseMessage<>(message,null),status);
    }
}
