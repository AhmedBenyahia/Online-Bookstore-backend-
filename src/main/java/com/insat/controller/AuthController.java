package com.insat.controller;


import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String password, @PathVariable String username) throws UnsupportedEncodingException {
        return  authService.login(username,password);
    }

}
