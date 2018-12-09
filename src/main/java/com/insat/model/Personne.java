package com.insat.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Date datebirth;

    @NotBlank
    private String Adresse;

    @NotBlank
    private String cin ;

    @NotBlank
    private String CodePostal;

    @NotBlank
    private String password;

    @NotBlank
    private String ville;

    @NotBlank
    private  String username;

    @NotBlank
    private  String role;

    public Personne(@NotBlank String name,
                    @NotBlank String surname,
                    Date datebirth,
                    @NotBlank String adresse,
                    @NotBlank String cin,
                    @NotBlank String codePostal,
                    @NotBlank String password,
                    @NotBlank String ville,
                    @NotBlank String username) {
        this.name = name;
        this.surname = surname;
        this.datebirth = datebirth;
        Adresse = adresse;
        this.cin = cin;
        CodePostal = codePostal;
        this.password = password;
        this.ville = ville;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
