/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.User;
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
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<User> getAll(){
        ResponseEntity<ResponseList<User>> response 
                = restTemplate.exchange(url + "/user", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<User>>(){});
        return response.getBody();
    }
    
    public ResponseData<User> getById(Integer id){
        ResponseEntity<ResponseData<User>> response
                = restTemplate.exchange(url + "/user/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<User>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<User> insert(User user){
        ResponseEntity<ResponseMessage<User>> response
                = restTemplate.exchange(url + "/user", HttpMethod.POST, 
                        new HttpEntity<>(user, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<User>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<User> update(Integer id, User user){
        ResponseEntity<ResponseMessage<User>> response
                = restTemplate.exchange(url + "/user/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(user, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<User>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<User> delete(Integer id){
        ResponseEntity<ResponseMessage<User>> response 
                = restTemplate.exchange(url + "/user/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<User>>() {});
        return response.getBody();
    }
    
}
