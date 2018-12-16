package com.insat.repository;

import com.insat.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
     Person findPersonByUsername(String username);
     Person findPersonByEmail(String email);

}
