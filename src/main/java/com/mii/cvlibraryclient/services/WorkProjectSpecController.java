/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.WorkProjectSpec;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Adhi
 */
@Service
public class WorkProjectSpecController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<WorkProjectSpec> getAll(){
        ResponseEntity<ResponseList<WorkProjectSpec>> response 
                = restTemplate.exchange(url + "/project_spec", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<WorkProjectSpec>>(){});
        return response.getBody();
    }
    
    public ResponseData<WorkProjectSpec> getLevelById(Integer id){
        ResponseEntity<ResponseData<WorkProjectSpec>> response
                = restTemplate.exchange(url + "/project_spec" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<WorkProjectSpec>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<WorkProjectSpec> postLevel(WorkProjectSpec workProjectSpec){
        ResponseEntity<ResponseMessage<WorkProjectSpec>> response
                = restTemplate.exchange(url + "/project_spec", HttpMethod.POST, 
                        new HttpEntity<>(workProjectSpec, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<WorkProjectSpec>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<WorkProjectSpec> putLevel(WorkProjectSpec workProjectSpec){
        ResponseEntity<ResponseMessage<WorkProjectSpec>> response
                = restTemplate.exchange(url + "/project_spec", HttpMethod.PUT, 
                        new HttpEntity<>(workProjectSpec, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<WorkProjectSpec>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<WorkProjectSpec> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<WorkProjectSpec>> response 
                = restTemplate.exchange(url + "/project_spec" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<WorkProjectSpec>>() {});
        return response.getBody();
    }
    
}
