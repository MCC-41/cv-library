/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.Permission;
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
public class PermissionService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("$(api.url)")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseData<Permission> getAll(){
        ResponseEntity<ResponseData<Permission>> response
                = restTemplate.exchange(url + "/permission", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Permission>>(){});
        return response.getBody();
    }
    
    public ResponseData<Permission> getPermissionById(Integer id){
        ResponseEntity<ResponseData<Permission>> response
                = restTemplate.exchange(url + "/permission" + id, HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Permission>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Permission> postPermission(String name){
        ResponseEntity<ResponseMessage<Permission>> response
                = restTemplate.exchange(url + "/permission", HttpMethod.POST, 
                        new HttpEntity<>(name, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Permission>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Permission> putPermission(String name){
        ResponseEntity<ResponseMessage<Permission>> response
                = restTemplate.exchange(url + "/permission", HttpMethod.PUT, 
                        new HttpEntity<>(name, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Permission>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Permission> deletePermission(Integer id){
        ResponseEntity<ResponseMessage<Permission>> response 
                = restTemplate.exchange(url + "/permission" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Permission>>() {});
        return response.getBody();
    }
    
}
