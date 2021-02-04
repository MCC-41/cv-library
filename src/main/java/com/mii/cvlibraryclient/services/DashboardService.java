/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Dashboard;
import com.mii.cvlibraryclient.models.data.ResponseData;
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
public class DashboardService {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${api.url}/dashboard")
    private String url;
    
    @Autowired
    private LoginService loginService;
    
    public ResponseData<Dashboard> allEmployee(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/total_employee", HttpMethod.GET, new HttpEntity(loginService.createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> allUser(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/total_user", HttpMethod.GET, new HttpEntity(loginService.createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
}
