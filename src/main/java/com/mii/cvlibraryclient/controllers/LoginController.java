/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mii.cvlibraryclient.modals.Login;
import com.mii.cvlibraryclient.modals.response.LoginResponse;
import com.mii.cvlibraryclient.services.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



/**
 *
 * @author Adhi
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService service;
    
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("titlePage","Login");
        return "logincoba";
    }
    
    @PostMapping("login-proses")
    public String postLogin(Login lg) throws JsonProcessingException {
        LoginResponse lr = service.postLogin(lg);
        System.out.println(lr.getSuccess()+" "+lr.getMessage());

        
        return "redirect:/dashboard";
    }
    
    
    
    @PostMapping("logout")
    public String postLogout(){
        service.postLogout();
        return "redirect:/login";
    }
    
    @GetMapping("home")
    public String home(){
        return "home";
    }
    
//   @PostMapping("loginProses")
//    public String postLogin(Login lg) throws JsonProcessingException {
//        String lr = service.postLogin(lg);
//        System.out.println(lr.getSuccess()+" "+lr.getMessage());
////        try {
////            lr = service.postLogin(lg);
////            System.out.println("-----");
////            
////        } catch (Exception e) {
////            if(lr.equals("failed")){
////            return "redirect:/login?error";
////            } else if(lr.equals("locked"))
////            return "redirect:/login?locked";
////            System.out.println("");
////        }
////        
////        System.out.println("-----/////-----");
//        return "redirect:/dashboard";
//    }
    
}
