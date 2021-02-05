/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.data.RequestMemo;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseList<Employee> getAll() {
        ResponseEntity<ResponseList<Employee>> response
                = restTemplate.exchange(url + "/employee", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Employee>>() {
                });
        return response.getBody();
    }

    public ResponseData<Employee> getById(Integer id) {
        ResponseEntity<ResponseData<Employee>> response
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseData<Employee>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Employee> insert(Employee employee, MultipartFile file){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
        } catch (IOException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpHeaders headers = service.createHeader();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));

        map.set("name", employee.getName());
        map.set("email", employee.getEmail());
        map.set("dateBirth", employee.getDateBirth());
        map.set("nation", employee.getNation());
        map.set("status", employee.getStatus());
        map.set("gender", employee.getGender());
        map.set("religion", employee.getReligion().getId());
        map.set("file", new HttpEntity<>(resource, fileHeaders));
        
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employees", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {
                });
        return response.getBody();
    }
    public ResponseMessage<Employee> insertWithoutFile(Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employee", HttpMethod.POST,
                        new HttpEntity<>(employee,service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Employee> update(Integer id, Employee employee, MultipartFile file){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
        } catch (IOException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpHeaders headers = service.createHeader();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));

        map.set("name", employee.getName());
        map.set("email", employee.getEmail());
        map.set("dateBirth", employee.getDateBirth());
        map.set("nation", employee.getNation());
        map.set("status", employee.getStatus());
        map.set("gender", employee.getGender());
        map.set("religion", employee.getReligion().getId());
        map.set("file", new HttpEntity<>(resource, fileHeaders));

        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employees/" + id, HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {
                });
        return response.getBody();
    }
    public ResponseMessage<Employee> updateWithoutFile(Integer id, Employee employee){
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.PUT,
                        new HttpEntity<>(employee,service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Employee> delete(Integer id) {
        ResponseEntity<ResponseMessage<Employee>> response
                = restTemplate.exchange(url + "/employee/" + id, HttpMethod.DELETE,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Employee>>() {
                });
        return response.getBody();
    }
    
     public ByteArrayResource getdown(Integer id) throws IOException {
        ResponseEntity<ByteArrayResource> response
                = restTemplate.exchange(url + "/employee/"+ id +"/photo", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        ByteArrayResource.class);
        return response.getBody();
    }
    public ResponseMessage<String> senEmail(RequestMemo memo) {
        ResponseEntity<ResponseMessage<String>> response
                = restTemplate.exchange(url + "/employee/send", HttpMethod.POST,
                        new HttpEntity<>(memo,service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<String>>() {
                });
        return response.getBody();
    }
}
