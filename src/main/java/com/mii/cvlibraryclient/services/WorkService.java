/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;


import com.mii.cvlibraryclient.modals.Work;
import com.mii.cvlibraryclient.modals.data.ResponseData;
import com.mii.cvlibraryclient.modals.data.ResponseList;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
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
public class WorkService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Work> getAll(){
        ResponseEntity<ResponseList<Work>> response 
                = restTemplate.exchange(url + "/work", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Work>>(){});
        System.out.println(response.getBody());
        return response.getBody();
    }
    
    public ResponseData<Work> getWorkById(Integer id){
        ResponseEntity<ResponseData<Work>> response
                = restTemplate.exchange(url + "/work" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Work>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Work> postWork(Work work){
        ResponseEntity<ResponseMessage<Work>> response
                = restTemplate.exchange(url + "/work", HttpMethod.POST, 
                        new HttpEntity<>(work, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Work>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Work> putWork(Work work){
        ResponseEntity<ResponseMessage<Work>> response
                = restTemplate.exchange(url + "/work", HttpMethod.PUT, 
                        new HttpEntity<>(work, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Work>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Work> deleteWork(Integer id){
        ResponseEntity<ResponseMessage<Work>> response 
                = restTemplate.exchange(url + "/work" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Work>>() {});
        return response.getBody();
    }
    
}
