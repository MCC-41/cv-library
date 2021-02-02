/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.Religion;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.ReligionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adhi
 */
@RequestMapping("religion")
@Controller
public class ReligionController {
    
    @Autowired
    private ReligionService religionService;
    
    @GetMapping("")
    @ResponseBody
    public List<Religion> religion(){
        List<Religion> religion = religionService.getAll().getData();
        return religion;
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseData<Religion> getById(@PathVariable Integer id){
        ResponseData<Religion> religion = religionService.getById(id);
        return religion;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<Religion> insert(@RequestBody Religion religion){
        return religionService.insert(religion);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Religion> update(@PathVariable Integer id, @RequestBody Religion religion){
      
        return religionService.update(id,religion);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Religion> delete(@PathVariable Integer id){
        return religionService.delete(id);
    }
    
}
