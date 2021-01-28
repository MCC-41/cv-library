/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Employee;
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


public class EmployeeService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    @Autowired
    private LoginService service;
    
    public ResponseList<Employee> getAll(){
        ResponseEntity<ResponseList<Employee>> response 
                = restTemplate.exchange(url + "/major", HttpMethod.GET, 
                        new HttpEntity<>(service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Employee>>(){});
        return response.getBody();
    }
    
    public ResponseList<Employee> getEmployeeById(Integer id){
        ResponseEntity<ResponseList<Employee>> response
                = restTemplate.exchange(url + "/major" + id, HttpMethod.GET, 
                        new HttpEntity<> (service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseList<Employee>>(){});
        return response.getBody();
    }
    
    public ResponseMessage<Employee> postEmployee(Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/major", HttpMethod.POST, 
                        new HttpEntity<>(employee, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
    }
    
    public ResponseMessage<Employee> putEmployee(Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/major", HttpMethod.PUT, 
                        new HttpEntity<>(employee, service.createHeaders()), 
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
        
    }
    
    public ResponseMessage<Employee> deleteEmployee(Integer id){
        ResponseEntity<ResponseMessage<Employee>> response 
                = restTemplate.exchange(url + "/major" + id, HttpMethod.DELETE, 
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {});
        return response.getBody();
    }
    
}
