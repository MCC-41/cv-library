/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Education;
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
public class EducationService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Education> getAll(){
        ResponseEntity<ResponseList<Education>> response 
                = restTemplate.exchange(url + "/education", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Education>>(){});
        return response.getBody();
    }
    
    public ResponseList<Education> getAllEducation(){
        Integer id = (service.getIdEmployee()==null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Education>> response 
                = restTemplate.exchange(url + "/education/" + id +"/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Education>>(){});
        return response.getBody();
    }
    
    public ResponseData<Education> getById(Integer id){
        ResponseEntity<ResponseData<Education>> response
                = restTemplate.exchange(url + "/education/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Education>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Education> insert(Education education){
        ResponseEntity<ResponseMessage<Education>> response
                = restTemplate.exchange(url + "/education", HttpMethod.POST, 
                        new HttpEntity<>(education, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Education>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Education> update(Integer id, Education education){
        ResponseEntity<ResponseMessage<Education>> response
                = restTemplate.exchange(url + "/education/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(education, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Education>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Education> delete(Integer id){
        ResponseEntity<ResponseMessage<Education>> response 
                = restTemplate.exchange(url + "/education/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Education>>() {});
        return response.getBody();
    }
    
    
    public ResponseList<Education> getAllById(Integer id){
        ResponseEntity<ResponseList<Education>> response 
                = restTemplate.exchange(url + "/education/" + id +"/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Education>>(){});
        return response.getBody();
    }
    
    public ResponseList<Education> getAllByEmployee(Integer id){
        ResponseEntity<ResponseList<Education>> response 
                = restTemplate.exchange(url + "/education/" + id +"/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Education>>(){});
        return response.getBody();
    }
}

    