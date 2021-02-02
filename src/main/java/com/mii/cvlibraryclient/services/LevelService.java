/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Level;
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
public class LevelService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Level> getAll(){
        ResponseEntity<ResponseList<Level>> response 
                = restTemplate.exchange(url + "/level", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Level>>(){});
        return response.getBody();
    }
    
    public ResponseData<Level> getById(Integer id){
        ResponseEntity<ResponseData<Level>> response
                = restTemplate.exchange(url + "/level/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Level>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Level> insert(Level level){
        ResponseEntity<ResponseMessage<Level>> response
                = restTemplate.exchange(url + "/level", HttpMethod.POST, 
                        new HttpEntity<>(level, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Level>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Level> update(Integer id, Level level){
        ResponseEntity<ResponseMessage<Level>> response
                = restTemplate.exchange(url + "/level/"+ id, HttpMethod.PUT, 
                        new HttpEntity<>(level, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Level>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Level> delete(Integer id){
        ResponseEntity<ResponseMessage<Level>> response 
                = restTemplate.exchange(url + "/level/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Level>>() {});
        return response.getBody();
    }
}
