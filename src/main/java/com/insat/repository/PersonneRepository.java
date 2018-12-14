package com.insat.repository;

import com.insat.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;


@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
     Personne findPersonneByUsername(String username);
}
