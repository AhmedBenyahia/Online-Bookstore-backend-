package com.insat.Interceptor;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.insat.model.Person;
import com.insat.security.JwToken;
import com.insat.service.AuthService;
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
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        if(token == null ){
            response.sendError(401);
            return false;
        }
        try {
                Person person = JwToken.validateToken(token,AuthService.SECRET);
                request.setAttribute("username", person.getUsername());
                System.out.println(" Token is valid:" + person.getUsername());
                return true;
            }catch( TokenExpiredException e2){
                    System.out.println(" Token has expired");
                    response.sendError(401);
                    return false;
            } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
                    System.out.println(" UnsupportedEncoding!!");
                    response.sendError(406);
                    return false;
            } catch (NullPointerException e3) {
//                    e.printStackTrace();
                    System.out.println(" personne is null!!");
                    response.sendError(500);
                    return false;
        }


    }


    }

