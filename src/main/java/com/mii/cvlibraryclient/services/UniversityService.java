/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.University;
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
public class UniversityService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<University> getAll(){
        ResponseEntity<ResponseList<University>> response 
                = restTemplate.exchange(url + "/university", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<University>>(){});
        return response.getBody();
    }
    
    public ResponseList<University> getUniversityById(Integer id){
        ResponseEntity<ResponseList<University>> response
                = restTemplate.exchange(url + "/university" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<University>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<University> postUniversity(University university){
        ResponseEntity<ResponseMessage<University>> response
                = restTemplate.exchange(url + "/university", HttpMethod.POST, 
                        new HttpEntity<>(university, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<University>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<University> putUniversity(University university){
        ResponseEntity<ResponseMessage<University>> response
                = restTemplate.exchange(url + "/university", HttpMethod.PUT, 
                        new HttpEntity<>(university, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<University>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<University> deleteUniversity(Integer id){
        ResponseEntity<ResponseMessage<University>> response 
                = restTemplate.exchange(url + "/major" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<University>>() {});
        return response.getBody();
    }
    
}
