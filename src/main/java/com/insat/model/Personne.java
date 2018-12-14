package com.insat.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "personne")
public class Personne {

    public Personne(@NotBlank String name, @NotBlank String surname,
                    @NotBlank String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public Personne(@NotBlank String name, @NotBlank String surname, Date birthdate, @NotBlank String address, @NotBlank String cin, @NotBlank String postcode, @NotBlank String password, @NotBlank String village, @NotBlank String username, @NotBlank String role, @NotBlank String email) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
        this.cin = cin;
        this.postcode = postcode;
        this.password = password;
        this.village = village;
        this.username = username;
        this.role = role;
        this.email = email;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Date birthdate;


    private String address;

    @NotBlank
    private String cin ;

    @NotBlank
    private String postcode;

    @NotBlank
    private String password;

    @NotBlank
    private String village;

    @NotBlank
    private  String username;


    private  String role;

    @NotBlank
    private String  email;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Personne(){}


}
