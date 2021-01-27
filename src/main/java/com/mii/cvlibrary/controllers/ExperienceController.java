/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Experience;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class ExperienceController implements IController<Experience, Integer>{

    @Autowired
    private ExperienceService es;
    
    @Override
    public ResponseList<Experience> getAll() {
        return new ResponseList(es.getAll());
    }

    @Override
    public ResponseRest<Experience> getById(Integer id) {
        return ResponseRest.success(es.getById(id));
    }

    @Override
    public ResponseRest<Experience> insert(Experience data) {
        try {
            return ResponseRest.success(es.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Experience> update(Integer id, Experience data) {
        try {
            return ResponseRest.success(es.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Experience> delete(Integer id) {
        if(es.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
