package com.insat.service;

import com.insat.model.Personne;

import java.util.Date;
import java.util.Optional;

public interface PersonneService {

    Personne addOne(Personne personne);
    void deleteById(Long id);
    Optional<Personne> getById(Long id);
}


//    String cin, String name, String surname,
//        String username, String adress,
//        String codePostal, String ville,
//        Date dateofbirth, String password