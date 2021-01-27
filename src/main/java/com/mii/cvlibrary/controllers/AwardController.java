/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Award;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class AwardController implements IController<Award, Integer>{

    @Autowired
    private AwardService as;
    
    @Override
    public ResponseList<Award> getAll() {
        return new ResponseList(as.getAll());
    }

    @Override
    public ResponseRest<Award> getById(Integer id) {
        return ResponseRest.success(as.getById(id));
    }

    @Override
    public ResponseRest<Award> insert(Award data) {
        try {
            return ResponseRest.success(as.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Award> update(Integer id, Award data) {
        try {
            return ResponseRest.success(as.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Award> delete(Integer id) {
        if(as.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
