/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Employee;
import com.mii.cvlibraryclient.models.Technical;
import com.mii.cvlibraryclient.models.TechnicalType;
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
public class TechnicalService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    @Autowired
    private LoginService service;

    public ResponseList<Technical> getAll() {
        ResponseEntity<ResponseList<Technical>> response
                = restTemplate.exchange(url + "/technical", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Technical>>() {
                });
        return response.getBody();
    }

    public ResponseList<Technical> getAllTechnical() {
        Integer id = (service.getIdEmployee() == null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Technical>> response
                = restTemplate.exchange(url + "/technical/" + id + "/employee", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Technical>>() {
                });
        return response.getBody();
    }

    public ResponseData<Technical> getById(Integer id) {
        ResponseEntity<ResponseData<Technical>> response
                = restTemplate.exchange(url + "/technical/" + id, HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseData<Technical>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Technical> insert(Technical technical) {
        TechnicalType tT = new TechnicalType();
        tT.setId(technical.getTechnicalType().getId());

        Employee em = new Employee();
        em.setId(technical.getEmployee().getId());

        Technical tech = new Technical();
        tech.setName(technical.getName());
        tech.setTechnicalType(tT);
        tech.setEmployee(em);

        ResponseEntity<ResponseMessage<Technical>> response
                = restTemplate.exchange(url + "/technical", HttpMethod.POST,
                        new HttpEntity<>(tech, service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Technical> update(Integer id, Technical technical) {

        TechnicalType tT = new TechnicalType();
        tT.setId(technical.getTechnicalType().getId());

        Employee em = new Employee();
        em.setId(technical.getEmployee().getId());

        Technical tech = new Technical();
        tech.setId(technical.getId());
        tech.setName(technical.getName());
        tech.setTechnicalType(tT);
        tech.setEmployee(em);
        ResponseEntity<ResponseMessage<Technical>> response
                = restTemplate.exchange(url + "/technical/" + id, HttpMethod.PUT,
                        new HttpEntity<>(technical, service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {
                });
        return response.getBody();

    }

    public ResponseMessage<Technical> delete(Integer id) {
        ResponseEntity<ResponseMessage<Technical>> response
                = restTemplate.exchange(url + "/technical/" + id, HttpMethod.DELETE,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Technical>>() {
                });
        return response.getBody();
    }

}
