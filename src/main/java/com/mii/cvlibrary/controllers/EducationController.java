/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Education;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class EducationController implements IController<Education, Integer>{

    @Autowired
    private EducationService es;
   
    @Override
    public ResponseList<Education> getAll() {
        return new ResponseList(es.getAll());
    }

    @Override
    public ResponseRest<Education> getById(Integer id) {
        return ResponseRest.success(es.getById(id));
    }

    @Override
    public ResponseRest<Education> insert(Education data) {
        try {
            return ResponseRest.success(es.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Education> update(Integer id, Education data) {
        try {
            return ResponseRest.success(es.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Education> delete(Integer id) {
        if(es.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
