package com.insat.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
//        Personne personne = personneRepository.findPersonneByUsername(username);
        Personne personnne = new Personne("slay","surname",new Date("12/06/1997"),
                "djerbaAjim","13462457","4135","154758168",
                "ajim","sayto");
        if (personnne == null) {
            return "username not exist !!";
        }else if(!password.equals(personnne.getPassword())){
            return "password does not match the username !!";
        } else {
            JwToken token = new JwToken(personnne,AuthService.EXPIRATION,AuthService.SECRET);
            return  "Bearer"+token.getToken();
        }
    }


    @Override
    public String renewToken(String token) throws UnsupportedEncodingException {
        Personne valid = JwToken.validateToken(token,AuthService.SECRET);
        if(valid != null){
            JwToken tok = new JwToken(valid,AuthService.EXPIRATION,AuthService.SECRET);
            return  tok.getToken();
        }
        return null;
    }
}
