package com.herokuapp.mrndesign.matned.model;

import javax.persistence.*;

@Entity
@Table(name = "VOTER_ENTITY")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "voters")
    private Candidate vote;

    public Voter() {
    }

    public Voter(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Voter(Long id, String name, String surname, Candidate vote) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.vote = vote;
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

    public Candidate getVote() {
        return vote;
    }

    public void setVote(Candidate vote) {
        this.vote = vote;
    }
}

