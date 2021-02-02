/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Award;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.services.LoginService;
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
public class AwardService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Award> getAll(){
        ResponseEntity<ResponseList<Award>> response 
                = restTemplate.exchange(url + "/award", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Award>>(){});
        return response.getBody();
    }
    
    public ResponseList<Award> getAllAward(){
        Integer id = (service.getIdEmployee()==null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Award>> response 
                = restTemplate.exchange(url + "/award/" + id + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Award>>(){});
        return response.getBody();
    }
    
    public ResponseData<Award> getById(Integer id){
        ResponseEntity<ResponseData<Award>> response
                = restTemplate.exchange(url + "/award/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Award>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Award> insert(Award award){
        ResponseEntity<ResponseMessage<Award>> response
                = restTemplate.exchange(url + "/award", HttpMethod.POST, 
                        new HttpEntity<>(award, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Award>>() {});
        return response.getBody();
    }
    

    public ResponseMessage<Award> update(Integer id,Award award){

        ResponseEntity<ResponseMessage<Award>> response
                = restTemplate.exchange(url + "/award/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(award, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Award>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Award> delete(Integer id){
        ResponseEntity<ResponseMessage<Award>> response 
                = restTemplate.exchange(url + "/award/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Award>>() {});
        return response.getBody();
    }
    
}
