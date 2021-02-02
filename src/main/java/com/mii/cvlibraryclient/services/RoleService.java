/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Role;
import com.mii.cvlibraryclient.models.data.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mii.cvlibraryclient.models.Role;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseMessage;

/**
 *
 * @author Adhi
 */
@Service
public class RoleService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Role> getAll(){
        ResponseEntity<ResponseList<Role>> response 
                = restTemplate.exchange(url + "/role", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Role>>(){});
        return response.getBody();
    }
    
    public ResponseData<Role> getById(Integer id){
        ResponseEntity<ResponseData<Role>> response
                = restTemplate.exchange(url + "/role/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Role>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Role> insert(Role role){
        ResponseEntity<ResponseMessage<Role>> response
                = restTemplate.exchange(url + "/role", HttpMethod.POST, 
                        new HttpEntity<>(role, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Role>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Role> update(Integer id,Role role){
        ResponseEntity<ResponseMessage<Role>> response
                = restTemplate.exchange(url + "/role/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(role, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Role>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Role> delete(Integer id){
        ResponseEntity<ResponseMessage<Role>> response 
                = restTemplate.exchange(url + "/role/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Role>>() {});
        return response.getBody();
    }
    
    
    
    
    
}
