package com.insat.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.insat.model.Personne;
import com.insat.service.AuthService;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwToken{


    private int expiration;

    private String secret ;

    private String token;


    public static Personne validateToken(String token, String secret)
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
                return new Personne(name, surname, username);
            } else {
                return null;
            }
        } catch(TokenExpiredException e){
            System.out.println(" Token has expired");
            return null;
        }
    }

    public JwToken(Personne personne, int expiration, String secret)
            throws UnsupportedEncodingException {
        this.expiration = expiration;
        this.secret = secret;

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        token = JWT.create()
                .withClaim("name",personne.getName())
                .withClaim("cin",personne.getCin())
                .withClaim("address",personne.getAddress())
                .withClaim("codepostal",personne.getPostcode())
                .withClaim("ville",personne.getVillage())
                .withClaim("surname",personne.getSurname())
                .withClaim("username",personne.getUsername())
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
