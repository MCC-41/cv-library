/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Religion;
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
public class ReligionService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Religion> getAll(){
        ResponseEntity<ResponseList<Religion>> response 
                = restTemplate.exchange(url + "/religion", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Religion>>(){});
        return response.getBody();
    }
    
    public ResponseData<Religion> getLevelById(Integer id){
        ResponseEntity<ResponseData<Religion>> response
                = restTemplate.exchange(url + "/religion" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Religion>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Religion> postLevel(Religion religion){
        ResponseEntity<ResponseMessage<Religion>> response
                = restTemplate.exchange(url + "/religion", HttpMethod.POST, 
                        new HttpEntity<>(religion, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Religion> putLevel(Religion religion){
        ResponseEntity<ResponseMessage<Religion>> response
                = restTemplate.exchange(url + "/religion", HttpMethod.PUT, 
                        new HttpEntity<>(religion, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Religion> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<Religion>> response 
                = restTemplate.exchange(url + "/religion" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
    }
    
    
    
    
    
}
