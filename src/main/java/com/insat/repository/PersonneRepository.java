package com.insat.repository;

import com.insat.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
     Personne findPersonneByUsername(String username);
}
