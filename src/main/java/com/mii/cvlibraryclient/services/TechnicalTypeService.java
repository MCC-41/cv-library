/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.TechnicalType;
import com.mii.cvlibraryclient.modals.data.ResponseData;
import com.mii.cvlibraryclient.modals.data.ResponseList;
import com.mii.cvlibraryclient.modals.data.ResponseMessage;
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
public class TechnicalTypeService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<TechnicalType> getAll(){
        ResponseEntity<ResponseList<TechnicalType>> response 
                = restTemplate.exchange(url + "/technical_type", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<TechnicalType>>(){});
        return response.getBody();
    }
    
    public ResponseData<TechnicalType> getLevelById(Integer id){
        ResponseEntity<ResponseData<TechnicalType>> response
                = restTemplate.exchange(url + "/technical_type" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<TechnicalType>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<TechnicalType> postLevel(TechnicalType technicalType){
        ResponseEntity<ResponseMessage<TechnicalType>> response
                = restTemplate.exchange(url + "/technical_type", HttpMethod.POST, 
                        new HttpEntity<>(technicalType, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<TechnicalType>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<TechnicalType> putLevel(TechnicalType technicalType){
        ResponseEntity<ResponseMessage<TechnicalType>> response
                = restTemplate.exchange(url + "/technical_type", HttpMethod.PUT, 
                        new HttpEntity<>(technicalType, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<TechnicalType>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<TechnicalType> deleteLevel(Integer id){
        ResponseEntity<ResponseMessage<TechnicalType>> response 
                = restTemplate.exchange(url + "/technical_type" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<TechnicalType>>() {});
        return response.getBody();
    }
    
}
