package com.insat.serviceImpl;

import com.insat.model.Person;
import com.insat.repository.PersonRepository;
import com.insat.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonneService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Person addOne(Person person) {
        if(personRepository.findPersonByUsername(person.getUsername()) != null ){
            person.setUsername(null);
            return person;
        } else if (personRepository.findPersonByEmail(person.getEmail()) != null){
            person.setEmail(null);
            return person;
        } else{
            person.setRole("user");
            person.setPassword(passwordEncoder.encode(person.getPassword()));
                return personRepository.save(person);
        }
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person getByUsername(String username) {
        return personRepository.findPersonByUsername(username);
    }
}
