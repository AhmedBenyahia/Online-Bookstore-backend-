package com.insat.controller;


import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login/{username}/")
    public ResponseEntity<String> login(@PathVariable String username,
                                        @RequestBody String password) throws UnsupportedEncodingException {
        System.out.println("login: "+username);
        return  new ResponseEntity<>(authService.login(username,password), HttpStatus.OK);
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
