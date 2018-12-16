package com.insat.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.insat.model.Person;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwToken{


    private int expiration;

    private String secret ;

    private String token;


    public static Person validateToken(String token, String secret)
            throws UnsupportedEncodingException, JWTVerificationException
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);


            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String username = jwt.getClaim("username").asString();
            String name = jwt.getClaim("name").asString();
            String surname = jwt.getClaim("surname").asString();
            String cin = jwt.getClaim("cin").asString();
            String codepostal = jwt.getClaim("codepostal").asString();
            String ville = jwt.getClaim("ville").asString();
            String adress = jwt.getClaim("adress").asString();

            if (cin != null) {
                return new Person(name, surname, username);
            } else {
                return null;
            }
        } catch(JWTDecodeException e){
           throw new UnsupportedEncodingException() ;
        }
    }

    public JwToken(Person person, int expiration, String secret)
            throws UnsupportedEncodingException {
        this.expiration = expiration;
        this.secret = secret;

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        token = JWT.create()
                .withClaim("name", person.getName())
                .withClaim("cin", person.getCin())
                .withClaim("address", person.getAddress())
                .withClaim("codepostal", person.getPostcode())
                .withClaim("ville", person.getVillage())
                .withClaim("surname", person.getSurname())
                .withClaim("username", person.getUsername())
                .withExpiresAt(new Date(this.expiration+System
                        .currentTimeMillis()))
                .sign(algorithm);
    }





    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    public String getToken() {
        return token;
    }
}
