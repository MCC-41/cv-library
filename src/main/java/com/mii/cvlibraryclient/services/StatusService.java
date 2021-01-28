/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Status;
import com.mii.cvlibraryclient.modals.data.ResponseData;
import com.mii.cvlibraryclient.modals.data.ResponseList;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
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
public class StatusService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Status> getAll(){
        ResponseEntity<ResponseList<Status>> response 
                = restTemplate.exchange(url + "/status", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Status>>(){});
        return response.getBody();
    }
    
    public ResponseData<Status> getLevelById(Integer id){
        ResponseEntity<ResponseData<Status>> response
                = restTemplate.exchange(url + "/status" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Status>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Status> postLevel(Status status){
        ResponseEntity<ResponseMessage<Status>> response
                = restTemplate.exchange(url + "/status", HttpMethod.POST, 
                        new HttpEntity<>(status, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Status>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Status> putLevel(Status status){
        ResponseEntity<ResponseMessage<Status>> response
                = restTemplate.exchange(url + "/status", HttpMethod.PUT, 
                        new HttpEntity<>(status, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Status>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Status> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<Status>> response 
                = restTemplate.exchange(url + "/status" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Status>>() {});
        return response.getBody();
    }
    
}
