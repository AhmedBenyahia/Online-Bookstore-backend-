package com.insat.service;


import java.io.UnsupportedEncodingException;
import java.util.Date;

public interface AuthService  {

    String SECRET = "This is the passwd for all the token," +
                                    " it should be well protected  !!";
    int EXPIRATION = 60*60*1000;

    boolean isAdmin(String username);

    String renewToken(String token) throws UnsupportedEncodingException;
   String login(String userName, String password) throws UnsupportedEncodingException;
   boolean isValid(String token) throws UnsupportedEncodingException;
}
