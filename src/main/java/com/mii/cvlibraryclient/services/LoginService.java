/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.services;

import com.mii.cvlibraryclient.modals.Login;
import com.mii.cvlibraryclient.modals.response.LoginDataResponse;
import com.mii.cvlibraryclient.modals.response.LoginResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 *
 * @author Adhi
 */

@Service
public class LoginService {
   @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}")
    private String url;
    
    public void setAuthority(String username,String password,List<String> listAuth){
        List<GrantedAuthority> list = new ArrayList<>();
        listAuth.forEach(action->{
            list.add(new SimpleGrantedAuthority(action));
        });
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password,list);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    }
    public LoginResponse postLogin(Login loginData){
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(url+"/login", loginData, LoginResponse.class);
        System.out.println(response.getStatusCode());
        LoginDataResponse data = response.getBody().getData();
        LoginResponse loginResponse = response.getBody();
        System.out.println(loginResponse.getMessage()+" "+loginResponse.getSuccess());
        if(response.getStatusCodeValue()==200){
            setAuthority(loginData.getUsername(), loginData.getPassword(), data.getAuthority());
        }
        return loginResponse;
    }
    
    public LoginResponse postLogout(){
        ResponseEntity<LoginResponse> response = restTemplate.exchange(url+"/logout",HttpMethod.POST ,new HttpEntity<>(createHeaders()), LoginResponse.class);
        LoginResponse lr = response.getBody();
        SecurityContextHolder.getContext().setAuthentication(null);
        return lr;
    }
    
    HttpHeaders createHeaders(){
        Authentication sc = SecurityContextHolder.getContext().getAuthentication();
        return new HttpHeaders() {{
            String auth = sc.getName() + ":" + sc.getCredentials();
            byte[] encodedAuth = Base64.encodeBase64( 
            auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
//            set(WARNING, RANGE);
       }};
    }
    
}
