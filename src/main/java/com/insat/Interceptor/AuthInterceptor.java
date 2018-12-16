package com.insat.Interceptor;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.insat.security.JwToken;
import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("preHandling");
        System.out.println("Request URL:" + request.getRequestURL());
        String token = request.getHeader("Authorization");
        System.out.println (request.getMethod());
        System.out.println("Authorization:" + token + ": redirect->");
            request.removeAttribute("Authorization");

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        try {
                JwToken.validateToken(token,AuthService.SECRET);
            }catch( TokenExpiredException e2){
                    System.out.println(" Token has expired");
                    response.sendError(401);
                    return false;
            } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    response.sendError(500);
                    return false;
            }

        return true;
    }


    }

