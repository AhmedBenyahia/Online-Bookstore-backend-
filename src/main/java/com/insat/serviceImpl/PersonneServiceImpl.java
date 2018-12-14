package com.insat.serviceImpl;

import com.insat.model.Personne;
import com.insat.repository.PersonneRepository;
import com.insat.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonneServiceImpl implements PersonneService {

    @Autowired
    PersonneRepository personneRepository;

    @Override
    public Personne addOne(Personne personne) {
        personne.setRole("user");
        if(personneRepository.findPersonneByUsername(personne.getUsername()) == null) {
            return personneRepository.save(personne);

        }else return null;
    }

    @Override
    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }

    @Override
    public Optional<Personne> getById(Long id) {
        return personneRepository.findById(id);
    }
}
