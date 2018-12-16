package com.insat.serviceImpl;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.insat.model.Person;
import com.insat.repository.PersonRepository;
import com.insat.security.JwToken;
import com.insat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.io.UnsupportedEncodingException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) throws UnsupportedEncodingException {
        try {
            Person person = personRepository.findPersonByUsername(username);
            if (person == null) {
                return "username not exist !!";
            }else if(!passwordEncoder.matches(password,person.getPassword())) {
                return "password does not match the username !!";
            } else {
                JwToken token = new JwToken(person,AuthService.EXPIRATION,AuthService.SECRET);
                return  "Bearer"+token.getToken();
            }
        }catch( NonUniqueResultException e ){
            return "there is a problem in the database\n" +
                    "username is not unique\n";
        }
    }

    @Override
    public boolean isValid(String token) {
        try {

//            System.out.println(" token is valid");
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
    public  boolean isAdmin(String username) {
        return personRepository.findPersonByUsername(username)
                .getRole()
                .toLowerCase()
                .equals("admin");
    }


    @Override
    public String renewToken(String token) throws UnsupportedEncodingException {
        Person person = JwToken.validateToken(token,AuthService.SECRET);
        if(person != null && personRepository.findPersonByUsername(person.getUsername()) != null){
            JwToken tok = new JwToken(person,AuthService.EXPIRATION,AuthService.SECRET);
            return  "Bearer"+tok.getToken();
        }
        return null;
    }


}
