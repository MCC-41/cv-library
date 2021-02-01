/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.UserPermission;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
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
public class UserPermissionService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<UserPermission> getAll(){
        ResponseEntity<ResponseList<UserPermission>> response 
                = restTemplate.exchange(url + "/user_permission", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<UserPermission>>(){});
        return response.getBody();
    }
    
    public ResponseData<UserPermission> getLevelById(Integer id){
        ResponseEntity<ResponseData<UserPermission>> response
                = restTemplate.exchange(url + "/user_permission" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<UserPermission>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<UserPermission> postLevel(UserPermission userPermission){
        ResponseEntity<ResponseMessage<UserPermission>> response
                = restTemplate.exchange(url + "/user_permission", HttpMethod.POST, 
                        new HttpEntity<>(userPermission, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<UserPermission>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<UserPermission> putLevel(UserPermission userPermission){
        ResponseEntity<ResponseMessage<UserPermission>> response
                = restTemplate.exchange(url + "/user_permission", HttpMethod.PUT, 
                        new HttpEntity<>(userPermission, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<UserPermission>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<UserPermission> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<UserPermission>> response 
                = restTemplate.exchange(url + "/user_permission" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<UserPermission>>() {});
        return response.getBody();
    }
    
}
