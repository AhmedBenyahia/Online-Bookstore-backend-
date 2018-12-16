package com.insat.service;

import com.insat.model.Person;

import java.util.Optional;

public interface PersonneService {

    Person addOne(Person person);
    void deleteById(Long id);
    Optional<Person> getById(Long id);
}


//    String cin, String name, String surname,
//        String username, String adress,
//        String codePostal, String ville,
//        Date dateofbirth, String password