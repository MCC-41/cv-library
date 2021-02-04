/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers.admin;
import com.mii.cvlibraryclient.models.Level;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("level")
@Controller
public class LevelController {
    
    @Autowired
    private LevelService levelService;
    
    @GetMapping("")
    public String page(Model model){
        return "admin-level";
    }
    
    @GetMapping("all")
    @ResponseBody
    public List<Level> level(){
        List<Level> level = levelService.getAll().getData();
        return level;
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseData<Level> getById(@PathVariable Integer id){
        ResponseData<Level> level = levelService.getById(id);
        return level;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage<Level> insert(@RequestBody Level level){
        return levelService.insert(level);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Level> update(@PathVariable Integer id, @RequestBody Level level){
      
        return levelService.update(id,level);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage<Level> delete(@PathVariable Integer id){
        return levelService.delete(id);
    }
    
}
