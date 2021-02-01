/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.cvlibraryclient.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mii.cvlibraryclient.models.Login;
import com.mii.cvlibraryclient.models.auth.AuthResponse;
import com.mii.cvlibraryclient.models.data.ResponseMessage;
import com.mii.cvlibraryclient.models.response.LoginResponse;
import com.mii.cvlibraryclient.services.LoginService;
import java.util.List;
import javax.xml.ws.http.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author Adhi
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("titlePage", "Login");
        return "logincoba";
    }

    @PostMapping("login_proses")
    public String postLogin(Login lg) throws JsonProcessingException {
        try {
            ResponseMessage<AuthResponse> lr = service.postLogin(lg);
            System.out.println(lr.getMessage());
            return "redirect:/dashboard";
        } catch (HttpClientErrorException e) {
            int response = e.getRawStatusCode();
            switch (response) {
                case 401:
                    return "redirect:/login?error";
                case 404:
                    return "redirect:/login?notFound";
                default:
                    return "redirect:/login?locked";
            }
        }
    }

    @PostMapping("logout")
    public String postLogout() {
        service.postLogout();
        return "redirect:/login";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }
}
