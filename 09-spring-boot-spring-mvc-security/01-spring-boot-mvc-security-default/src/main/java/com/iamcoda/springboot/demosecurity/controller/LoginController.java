package com.iamcoda.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showLoginPage(){
        return "plain-login";
    }

}
