package com.herokuapp.mrndesign.matned.model;

import javax.persistence.*;

@Entity
@Table(name = "VOTER_ENTITY")
public class Voter {

    public static final String ROLE_NOT_FOUND = "Role not found";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    public Voter() {
    }

    public Voter(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public Long getId() {
        return id;
    }
}

