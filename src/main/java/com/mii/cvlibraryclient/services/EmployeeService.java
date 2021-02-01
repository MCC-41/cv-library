/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Employee;
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
public class EmployeeService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Employee> getAll(){
        ResponseEntity<ResponseList<Employee>> response 
                = restTemplate.exchange(url + "/employee", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Employee>>(){});
        return response.getBody();
    }
    
    public ResponseData<Employee> getById(Integer id){
        ResponseEntity<ResponseData<Employee>> response
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseData<Employee>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Employee> insert(Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employee", HttpMethod.POST, 
                        new HttpEntity<>(employee, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Employee> update(Integer id, Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.PUT, 
                        new HttpEntity<>(employee, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Employee> delete(Integer id){
        ResponseEntity<ResponseMessage<Employee>> response 
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
    }
    
}
