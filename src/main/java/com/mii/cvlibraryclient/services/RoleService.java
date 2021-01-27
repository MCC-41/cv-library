/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.data.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mii.cvlibraryclient.modals.Role;
import com.mii.cvlibraryclient.modals.data.ResponseData;

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
    
//    public ResponseList<Role> getAll(){
//        ResponseEntity<ResponseList<Role>> response 
//                = restTemplate.exchange(url+"/country", HttpMethod.GET,
//                        new HttpEntity<>(service.createHeaders()), .class);
//        ResponseList<Role> data = response.getBody();
//        List<Country> country = data.getData();
//        return country;
//    }
    
    
    
}
