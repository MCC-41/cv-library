/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Experience;
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
public class ExperienceService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Experience> getAll(){
        ResponseEntity<ResponseList<Experience>> response 
                = restTemplate.exchange(url + "/experience", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Experience>>(){});
        return response.getBody();
    }
    
    public ResponseList<Experience> getAllExperience(){
        Integer id = (service.getIdEmployee()==null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Experience>> response 
                = restTemplate.exchange(url + "/experience/" + id + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Experience>>(){});
        return response.getBody();
    }
    
    public ResponseData<Experience> getById(Integer id){
        ResponseEntity<ResponseData<Experience>> response
                = restTemplate.exchange(url + "/experience" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Experience>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Experience> insert(Experience experience){
        ResponseEntity<ResponseMessage<Experience>> response
                = restTemplate.exchange(url + "/experience", HttpMethod.POST, 
                        new HttpEntity<>(experience, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Experience>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Experience> update(Integer id,Experience experience){
        ResponseEntity<ResponseMessage<Experience>> response
                = restTemplate.exchange(url + "/experience/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(experience, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Experience>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Experience> delete(Integer id){
        ResponseEntity<ResponseMessage<Experience>> response 
                = restTemplate.exchange(url + "/experience/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Experience>>() {});
        return response.getBody();
    }
    
    public ResponseList<Experience> getAllByEmployee(Integer id){
        ResponseEntity<ResponseList<Experience>> response 
                = restTemplate.exchange(url + "/experience/" + id + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Experience>>(){});
        return response.getBody();
    }
}
