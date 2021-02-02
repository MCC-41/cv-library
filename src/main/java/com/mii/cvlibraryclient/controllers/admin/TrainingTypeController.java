/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;


import com.mii.cvlibraryclient.models.TrainingType;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.TrainingTypeService;
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
@RequestMapping("training_type")
@Controller
public class TrainingTypeController {
    
    @Autowired
    private TrainingTypeService trainingTypeService;
    
    @GetMapping("")
    @ResponseBody
    public List<TrainingType> trainingType(){
        List<TrainingType> trainingType = trainingTypeService.getAll().getData();
        return trainingType;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<TrainingType> getById(@PathVariable Integer id){
        ResponseData<TrainingType> trainingType = trainingTypeService.getById(id);
        return trainingType;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<TrainingType> insert(@RequestBody TrainingType trainingType){
        return trainingTypeService.insert(trainingType);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<TrainingType> update(@PathVariable Integer id,@RequestBody TrainingType trainingType){
        return trainingTypeService.update(id,trainingType);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<TrainingType> delete(@PathVariable Integer id){
        return trainingTypeService.delete(id);
    }
    
}
