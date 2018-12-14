package com.insat.controller;


import com.insat.model.Book;
import com.insat.model.Personne;
import com.insat.service.AuthService;
import com.insat.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonneController {

    @Autowired
    PersonneService personneService;

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<Personne> addPerson(@Valid @RequestBody Personne personne)
           {
        System.out.println("add a person " + personne.getRole());
            return new ResponseEntity<>(personneService.addOne(personne),
                    HttpStatus.OK);

    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePerson(@PathVariable ("id") Long id,
                                     @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("delete  person "+token);
        if(authService.isValid(token) && authService.role(token).equals("admin")) {
            personneService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Personne>> getPersonById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("get person by id "+token);
        if(authService.isValid(token) && authService.role(token).equals("admin")) {
            return new ResponseEntity<>(personneService.getById(id), HttpStatus.OK);
        }  else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public ResponseEntity<Personne> updateBook(
            @RequestBody Personne newPerson,
            @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("update person \n -> " + token);
        if (authService.isValid(token) && authService.role(token).equals("admin")) {
            Optional<Personne> oldPerson = getPersonById(newPerson.getId(),token).getBody();
            if (oldPerson.isPresent()) {
                return new ResponseEntity<Personne>(addPerson(newPerson).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
            }
        } return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
