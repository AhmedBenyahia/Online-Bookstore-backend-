package com.insat.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin extends Personne {

    public Admin(String username, @NotBlank String name, @NotBlank String surname, Date datebirth, @NotBlank String adresse, @NotBlank String cin, @NotBlank String codePostal, @NotBlank String password, @NotBlank String ville) {
        super(name, surname, datebirth, adresse, cin, codePostal, password, ville, username);
    }
}
