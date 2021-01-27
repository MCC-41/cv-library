/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Level;
import com.mii.cvlibraryclient.modals.data.ResponseList;
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
    
    public ResponseList<Level> getLevelById(Integer id){
        ResponseEntity<ResponseList<Level>> response
                = restTemplate.exchange(url + "/level" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Level>>(){});
        return response.getBody();
    }
    
    public ResponseList<Level> postCountry(Level level){
        ResponseEntity<ResponseList<Level>> response
                = restTemplate.exchange(url + "/level", HttpMethod.POST, 
                        new HttpEntity<>(level, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Level>>() {});
        return response.getBody();
    }
    
    public ResponseList<Level> putCountry(Level level){
        ResponseEntity<ResponseList<Level>> response
                = restTemplate.exchange(url + "/level", HttpMethod.PUT, 
                        new HttpEntity<>(level, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Level>>() {});
        return response.getBody();
        
    }
    
    public ResponseList<Level> deleteCountry(Integer id){
        ResponseEntity<ResponseList<Level>> response 
                = restTemplate.exchange(url + "/level" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Level>>() {});
        return response.getBody();
    }
}
