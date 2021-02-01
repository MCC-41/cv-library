/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Training;
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
public class TrainingService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Training> getAll(){
        ResponseEntity<ResponseList<Training>> response 
                = restTemplate.exchange(url + "/training", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Training>>(){});
        return response.getBody();
    }
    
    public ResponseList<Training> getAllTraining(){
        Integer id = (service.getIdEmployee()==null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Training>> response 
                = restTemplate.exchange(url + "/training/" + id + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Training>>(){});
        return response.getBody();
    }
    
    public ResponseData<Training> getById(Integer id){
        ResponseEntity<ResponseData<Training>> response
                = restTemplate.exchange(url + "/training/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Training>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Training> insert(Training training){
        ResponseEntity<ResponseMessage<Training>> response
                = restTemplate.exchange(url + "/training", HttpMethod.POST, 
                        new HttpEntity<>(training, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Training> update(Integer id,Training training){
        ResponseEntity<ResponseMessage<Training>> response
                = restTemplate.exchange(url + "/training/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(training, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Training> delete(Integer id){
        ResponseEntity<ResponseMessage<Training>> response 
                = restTemplate.exchange(url + "/training/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {});
        return response.getBody();
    }
    
    
    
}
