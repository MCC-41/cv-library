/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Technical;
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
public class TechnicalService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Technical> getAll(){
        ResponseEntity<ResponseList<Technical>> response 
                = restTemplate.exchange(url + "/technical", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Technical>>(){});
        return response.getBody();
    }
    
    public ResponseData<Technical> getLevelById(Integer id){
        ResponseEntity<ResponseData<Technical>> response
                = restTemplate.exchange(url + "/technical" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Technical>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Technical> postLevel(Technical technical){
        ResponseEntity<ResponseMessage<Technical>> response
                = restTemplate.exchange(url + "/technical", HttpMethod.POST, 
                        new HttpEntity<>(technical, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Technical> putLevel(Technical technical){
        ResponseEntity<ResponseMessage<Technical>> response
                = restTemplate.exchange(url + "/technical", HttpMethod.PUT, 
                        new HttpEntity<>(technical, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Technical> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<Technical>> response 
                = restTemplate.exchange(url + "/technical" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {});
        return response.getBody();
    }
    
}
