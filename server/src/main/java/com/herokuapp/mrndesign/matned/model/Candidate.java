package com.herokuapp.mrndesign.matned.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CANDIDATE_ENTITY")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Voter voter;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Voter> voters = new ArrayList<>();

    public Candidate() {
    }

    public Candidate(Voter voter, List<Voter> voters) {
        this.voter = voter;
        this.voters = voters;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Long getId() {
        return id;
    }
}

