/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;



import com.mii.cvlibraryclient.models.TechnicalType;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.TechnicalTypeService;
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
@RequestMapping("technical_type")
@Controller
public class TechnicalTypeController {
    
    @Autowired
    private TechnicalTypeService technicalTypeService;
    
    @GetMapping("")
    @ResponseBody
    public List<TechnicalType> technicalType(){
        List<TechnicalType> technicalType = technicalTypeService.getAll().getData();
        return technicalType;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<TechnicalType> getById(@PathVariable Integer id){
        ResponseData<TechnicalType> technicalType = technicalTypeService.getById(id);
        return technicalType;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<TechnicalType> insert(@RequestBody TechnicalType technicalType){
        return technicalTypeService.insert(technicalType);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ResponseMessage<TechnicalType> update(@PathVariable Integer id,@RequestBody TechnicalType technicalType){
        return technicalTypeService.update(id,technicalType);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<TechnicalType> delete(@PathVariable Integer id){
        return technicalTypeService.delete(id);
    }
    
}
