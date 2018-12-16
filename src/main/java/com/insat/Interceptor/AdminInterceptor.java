package com.insat.Interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.insat.security.JwToken;
import com.insat.service.AuthService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        try {
            JwToken.validateToken(token, AuthService.SECRET);
            System.out.println(" Token is valid");
            ;
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
        }
    }
}
