/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;



import com.mii.cvlibraryclient.models.University;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.UniversityService;
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
@RequestMapping("university")
@Controller
public class UniversityController {
    
    @Autowired
    private UniversityService universityService;
    
    @GetMapping("")
    @ResponseBody
    public List<University> university(){
        List<University> university = universityService.getAll().getData();
        return university;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public ResponseData<University> getById(@PathVariable Integer id){
        ResponseData<University> university = universityService.getById(id);
        return university;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<University> insert(@RequestBody University university){
        return universityService.insert(university);
    }
    
    @PutMapping("{id}")
    @ResponseBody
    public ResponseMessage<University> update(@PathVariable Integer id, @RequestBody University university){
        return universityService.update(id,university);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<University> delete(@PathVariable Integer id){
        return universityService.delete(id);
    }
    
    
}
