package com.insat.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String description;
}
