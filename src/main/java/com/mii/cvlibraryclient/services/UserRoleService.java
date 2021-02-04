/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.UserRole;
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
public class UserRoleService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<UserRole> getAll(){
        ResponseEntity<ResponseList<UserRole>> response 
                = restTemplate.exchange(url + "/user_role", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<UserRole>>(){});
        return response.getBody();
    }
    
    public ResponseData<UserRole> getById(Integer id){
        ResponseEntity<ResponseData<UserRole>> response
                = restTemplate.exchange(url + "/user_role" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<UserRole>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<UserRole> insert(UserRole userRole){
        ResponseEntity<ResponseMessage<UserRole>> response
                = restTemplate.exchange(url + "/user_role", HttpMethod.POST, 
                        new HttpEntity<>(userRole, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<UserRole>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<UserRole> update(Integer id, UserRole userRole){
        ResponseEntity<ResponseMessage<UserRole>> response
                = restTemplate.exchange(url + "/user_role/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(userRole, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<UserRole>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<UserRole> delete(Integer id){
        ResponseEntity<ResponseMessage<UserRole>> response 
                = restTemplate.exchange(url + "/user_role/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<UserRole>>() {});
        return response.getBody();
    }
    
}
