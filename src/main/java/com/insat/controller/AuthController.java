package com.insat.controller;


import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = "*")
    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String password, @PathVariable String username) throws UnsupportedEncodingException {
        return  authService.login(username,password);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/renew/token")
    public String resendToken(@Valid @RequestBody String token) throws UnsupportedEncodingException {
        String newtoken = authService.renewToken(token);
        if(newtoken == null){
            return "token has expired";
        } else {
            return newtoken;
        }
    }

}
