/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.models.Training;
import com.mii.cvlibraryclient.models.data.ResponseData;
import com.mii.cvlibraryclient.models.data.ResponseList;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import java.io.IOException;
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
public class TrainingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    @Autowired
    private LoginService service;

    public ResponseList<Training> getAll() {
        ResponseEntity<ResponseList<Training>> response
                = restTemplate.exchange(url + "/training", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Training>>() {
                });
        return response.getBody();
    }

    public ResponseList<Training> getAllTraining() {
        Integer id = (service.getIdEmployee() == null) ? 0 : service.getIdEmployee();
        ResponseEntity<ResponseList<Training>> response
                = restTemplate.exchange(url + "/training/" + id + "/employee", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseList<Training>>() {
                });
        return response.getBody();
    }

    public ResponseData<Training> getById(Integer id) {
        ResponseEntity<ResponseData<Training>> response
                = restTemplate.exchange(url + "/training/" + id, HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseData<Training>>() {
                });
        return response.getBody();
    }

    public ByteArrayResource getdown() {
        Integer id = 2;
        ResponseEntity<ByteArrayResource> response
                = restTemplate.exchange(url + "/training/2/download", HttpMethod.GET,
                        new HttpEntity<>(service.createHeaders()),
                        ByteArrayResource.class);
        return response.getBody();
    }

    public ResponseMessage<Training> insert(Training training, MultipartFile file) throws IOException {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        System.out.println(resource);
        map.set("name", training.getName());
        map.set("institution", training.getInstitution());
        map.set("year", training.getYear());
        map.set("trainingType", training.getTrainingType().getId().toString());
        map.set("employee", training.getEmployee().getId().toString());
        map.set("file", resource);

        HttpHeaders headers = service.createHeader();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<ResponseMessage<Training>> response
                = restTemplate.exchange(url + "/trainings", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {
                });
        return response.getBody();
    }

    public ResponseMessage<Training> update(Integer id, Training training, MultipartFile file) throws IOException {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        HttpHeaders headers = service.createHeader();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));

        map.set("name", training.getName());
        map.set("institution", training.getInstitution());
        map.set("year", training.getYear());
        map.set("trainingType", training.getTrainingType().getId().toString());
        map.set("employee", training.getEmployee().getId().toString());
        map.set("file", new HttpEntity<>(resource,fileHeaders));
        
        ResponseEntity<ResponseMessage<Training>> response
                = restTemplate.exchange(url + "/trainings/" + id, HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {
                });
        return response.getBody();

    }

    public ResponseMessage<Training> delete(Integer id) {
        ResponseEntity<ResponseMessage<Training>> response
                = restTemplate.exchange(url + "/training/" + id, HttpMethod.DELETE,
                        new HttpEntity<>(service.createHeaders()),
                        new ParameterizedTypeReference<ResponseMessage<Training>>() {
                });
        return response.getBody();
    }
}
