package com.insat.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.insat.model.Personne;
import com.insat.repository.PersonneRepository;
import com.insat.security.JwToken;
import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    PersonneRepository personneRepository;

    @Override
    public String login(String username, String password) throws UnsupportedEncodingException {
        Personne personne = personneRepository.findPersonneByUsername(username);
//        Personne personne = new Personne("slay","surname",new Date("12/06/1997"),
//                    "djerbaAjim","13462457","4135","154758168",
//                "ajim","sayto");
        if (personne == null) {
            return "username not exist !!";
        }else if(!password.equals(personne.getPassword())){
            return "password does not match the username !!";
        } else {
            JwToken token = new JwToken(personne,AuthService.EXPIRATION,AuthService.SECRET);
            return  "Bearer"+token.getToken();
        }
    }

    @Override
    public boolean isValid(String token) {
        try {

            System.out.println(" trying to validate token");
            return  JwToken.validateToken(token,AuthService.SECRET) != null;
        }catch( TokenExpiredException e2){
            System.out.println(" Token has expired");
//            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String role(String token) throws UnsupportedEncodingException {
        Personne personne = JwToken.validateToken(token,AuthService.SECRET);

        return personneRepository.findPersonneByUsername(personne.getUsername()).getRole() ;
    }


    @Override
    public String renewToken(String token) throws UnsupportedEncodingException {
        Personne valid = JwToken.validateToken(token,AuthService.SECRET);
        if(valid != null){
            JwToken tok = new JwToken(valid,AuthService.EXPIRATION,AuthService.SECRET);
            return  "Bearer"+tok.getToken();
        }
        return null;
    }


}
