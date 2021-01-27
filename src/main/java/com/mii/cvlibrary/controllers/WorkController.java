/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Work;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class WorkController implements IController<Work, Integer>{

    @Autowired
    private WorkService ws;
    
    @Override
    public ResponseList<Work> getAll() {
        return new ResponseList(ws.getAll());
    }

    @Override
    public ResponseRest<Work> getById(Integer id) {
        return ResponseRest.success(ws.getById(id));
    }

    @Override
    public ResponseRest<Work> insert(Work data) {
        try {
            return ResponseRest.success(ws.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Work> update(Integer id, Work data) {
        try {
            return ResponseRest.success(ws.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Work> delete(Integer id) {
        if(ws.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
