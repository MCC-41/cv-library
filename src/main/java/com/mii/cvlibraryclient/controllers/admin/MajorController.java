/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;

import com.mii.cvlibraryclient.models.Major;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.MajorService;
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
@RequestMapping("major")
@Controller
public class MajorController {
    
    @Autowired
    private MajorService majorService;
    
    @GetMapping("")
    @ResponseBody
    public List<Major> major(){
        List<Major> major = majorService.getAll().getData();
        return major;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<Major> getById(@PathVariable Integer id){
        ResponseData<Major> major = majorService.getById(id);
        return major;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<Major> insert(@RequestBody Major major){
        return majorService.insert(major);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Major> update(@PathVariable Integer id, Major major){
        return majorService.update(id, major);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Major> delete(@PathVariable Integer id){
        return majorService.delete(id);
    }
    
    
}
