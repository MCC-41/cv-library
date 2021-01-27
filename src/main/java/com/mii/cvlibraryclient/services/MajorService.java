/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Major;
import com.mii.cvlibraryclient.modals.data.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Adhi
 */
public class MajorService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Major> getAll(){
        ResponseEntity<ResponseList<Major>> response 
                = restTemplate.exchange(url + "/major", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Major>>(){});
        return response.getBody();
    }
    
    public ResponseList<Major> getLevelById(Integer id){
        ResponseEntity<ResponseList<Major>> response
                = restTemplate.exchange(url + "/major" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Major>>(){});
        return response.getBody();
    }
    
    public ResponseList<Major> postCountry(Major major){
        ResponseEntity<ResponseList<Major>> response
                = restTemplate.exchange(url + "/major", HttpMethod.POST, 
                        new HttpEntity<>(major, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Major>>() {});
        return response.getBody();
    }
    
    public ResponseList<Major> putCountry(Major major){
        ResponseEntity<ResponseList<Major>> response
                = restTemplate.exchange(url + "/major", HttpMethod.PUT, 
                        new HttpEntity<>(major, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Major>>() {});
        return response.getBody();
        
    }
    
    public ResponseList<Major> deleteCountry(Integer id){
        ResponseEntity<ResponseList<Major>> response 
                = restTemplate.exchange(url + "/major" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Major>>() {});
        return response.getBody();
    }
    
}
