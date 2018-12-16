package com.insat.controller;


import com.insat.model.Person;
import com.insat.service.AuthService;
import com.insat.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonneService personneService;

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person)
           {
        System.out.println("add a person " + person.getRole());
        Person p = personneService.addOne(person);
            if(p == null) new ResponseEntity<>(p,HttpStatus.CONFLICT);
            return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePerson(@PathVariable ("id") Long id,
                                       @RequestAttribute ("username") String username) {
            System.out.println("delete  person "+ username);
        if(authService.isAdmin(username)) {
            personneService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Person>> getPersonById(
            @PathVariable("id") Long id,
            @RequestAttribute ("username") String username) {
        System.out.println("get person by id ");
        if(authService.isAdmin(username))
            return new ResponseEntity<>(personneService.getById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

}

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public ResponseEntity<Person> updateBook(
            @RequestBody Person newPerson,
            @RequestAttribute ("username") String username) {
        System.out.println("update person \n -> ");
        if (authService.isAdmin(username)) {
                Optional<Person> oldPerson = getPersonById(newPerson.getId(),username).getBody();
            if (oldPerson.isPresent()) {
                return new ResponseEntity<Person>(addPerson(newPerson).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
            }
       } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
}
}
