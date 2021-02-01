/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.TrainingType;
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
public class TrainingTypeService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<TrainingType> getAll(){
        ResponseEntity<ResponseList<TrainingType>> response 
                = restTemplate.exchange(url + "/training_type", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<TrainingType>>(){});
        return response.getBody();
    }
    
    public ResponseData<TrainingType> getLevelById(Integer id){
        ResponseEntity<ResponseData<TrainingType>> response
                = restTemplate.exchange(url + "/training_type" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<TrainingType>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<TrainingType> postLevel(TrainingType trainingType){
        ResponseEntity<ResponseMessage<TrainingType>> response
                = restTemplate.exchange(url + "/training_type", HttpMethod.POST, 
                        new HttpEntity<>(trainingType, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<TrainingType>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<TrainingType> putLevel(TrainingType trainingType){
        ResponseEntity<ResponseMessage<TrainingType>> response
                = restTemplate.exchange(url + "/training_type", HttpMethod.PUT, 
                        new HttpEntity<>(trainingType, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<TrainingType>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<TrainingType> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<TrainingType>> response 
                = restTemplate.exchange(url + "/training_type" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<TrainingType>>() {});
        return response.getBody();
    }
    
}
