/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.controllers.icontrollers.IController;
import com.mii.cvlibrary.models.Organization;
import com.mii.cvlibrary.models.data.ResponseList;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
public class OrganizationController implements IController<Organization, Integer>{

    @Autowired
    private OrganizationService os;
    
    @Override
    public ResponseList<Organization> getAll() {
        return new ResponseList(os.getAll());
    }

    @Override
    public ResponseRest<Organization> getById(Integer id) {
        return ResponseRest.success(os.getById(id));
    }

    @Override
    public ResponseRest<Organization> insert(Organization data) {
        try {
            return ResponseRest.success(os.insert(data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Organization> update(Integer id, Organization data) {
        try {
            return ResponseRest.success(os.update(id,data), "Success");
        } catch (Exception e) {
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseRest<Organization> delete(Integer id) {
        if(os.delete(id)){
            return ResponseRest.success("Success");
        }else{
            return ResponseRest.failed("Failed", HttpStatus.BAD_REQUEST);
        }
    }
    
}
