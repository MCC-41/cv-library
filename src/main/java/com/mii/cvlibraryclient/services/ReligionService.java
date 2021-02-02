/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Religion;
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
public class ReligionService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Religion> getAll(){
        ResponseEntity<ResponseList<Religion>> response 
                = restTemplate.exchange(url + "/religion/", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Religion>>(){});
        return response.getBody();
    }
    
    public ResponseData<Religion> getById(Integer id){
        ResponseEntity<ResponseData<Religion>> response
                = restTemplate.exchange(url + "/religion/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Religion>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Religion> insert(Religion religion){
        ResponseEntity<ResponseMessage<Religion>> response
                = restTemplate.exchange(url + "/religion", HttpMethod.POST, 
                        new HttpEntity<>(religion, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Religion> update(Integer id, Religion religion){
        ResponseEntity<ResponseMessage<Religion>> response
                = restTemplate.exchange(url + "/religion/" +id, HttpMethod.PUT, 
                        new HttpEntity<>(religion, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Religion> delete(Integer id){
        ResponseEntity<ResponseMessage<Religion>> response 
                = restTemplate.exchange(url + "/religion/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Religion>>() {});
        return response.getBody();
    }
    
    
    
    
    
}
