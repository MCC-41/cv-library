/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibrary.controllers;

import com.mii.cvlibrary.models.MyUserDetail;
import com.mii.cvlibrary.models.User;
import com.mii.cvlibrary.models.auth.AuthRequest;
import com.mii.cvlibrary.models.auth.AuthResponse;
import com.mii.cvlibrary.models.data.ResponseMessage;
import com.mii.cvlibrary.models.data.ResponseRest;
import com.mii.cvlibrary.services.MyUserDetailService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author habib
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailService service;

    @PostMapping("login")
    public ResponseRest<AuthResponse> login(@RequestBody AuthRequest user) {
        MyUserDetail detail = (MyUserDetail) service.loadUserByUsername(user.getUsername());
        if (detail.isAccountNonLocked()) {
            try {
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                Authentication auth = manager.authenticate(authReq);
                SecurityContext sc = SecurityContextHolder.getContext();
                sc.setAuthentication(auth);
                System.out.println("berhasil");
                return ResponseRest.success(SuccessResponse(detail), "Success");
            } catch (Exception e) {
                return ResponseRest.failed("Failed", HttpStatus.UNAUTHORIZED);
            }
            
        } else {
            return ResponseRest.failed("Locked", HttpStatus.LOCKED);
        }
    }
    
    @PostMapping("logout")
    public ResponseRest<String> logout(HttpServletRequest request){
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseRest.success("Logout");
    }
    
    private AuthResponse SuccessResponse(MyUserDetail userDetail){
        List<String>  authority = new ArrayList<>();
        userDetail.getAuthorities().forEach(item->{
             authority.add(item.getAuthority());
        });
        return new AuthResponse(userDetail.getUsername(), authority);
    }
}
