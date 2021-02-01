/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Organization;
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
public class OrganizationService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Organization> getAll(){
        ResponseEntity<ResponseList<Organization>> response 
                = restTemplate.exchange(url + "/organization", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Organization>>(){});
        return response.getBody();
    }
    
    public ResponseList<Organization> getAllOrganization(){
        Integer id = (service.getIdEmployee()==null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Organization>> response 
                = restTemplate.exchange(url + "/organization/" + id + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Organization>>(){});
        return response.getBody();
    }
    
    public ResponseData<Organization> getById(Integer id){
        ResponseEntity<ResponseData<Organization>> response
                = restTemplate.exchange(url + "/organization/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Organization>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Organization> insert(Organization organization){
        ResponseEntity<ResponseMessage<Organization>> response
                = restTemplate.exchange(url + "/organization", HttpMethod.POST, 
                        new HttpEntity<>(organization, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Organization>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Organization> update(Integer id,Organization organization){
        ResponseEntity<ResponseMessage<Organization>> response
                = restTemplate.exchange(url + "/organization/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(organization, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Organization>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Organization> delete(Integer id){
        ResponseEntity<ResponseMessage<Organization>> response 
                = restTemplate.exchange(url + "/organization/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Organization>>() {});
        return response.getBody();
    }
    
}
