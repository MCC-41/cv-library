/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Technical;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class TechnicalController implements IController<Technical, Integer>{

    @Autowired
    private TechnicalService ts;
    
    @Override
    public ResponseList<Technical> getAll() {
        return new ResponseList(ts.getAll());
    }

    @Override
    public ResponseRest<Technical> getById(Integer id) {
        return ResponseRest.success(ts.getById(id));
    }

    @Override
    public ResponseRest<Technical> insert(Technical data) {
        try {
            return ResponseRest.success(ts.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Technical> update(Integer id, Technical data) {
        try {
            return ResponseRest.success(ts.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Technical> delete(Integer id) {
        if(ts.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
