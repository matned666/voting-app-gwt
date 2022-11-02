package com.herokuapp.mrndesign.matned.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CANDIDATE_ENTITY")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Voter voter;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote")
    private Set<Voter> voters = new HashSet<>();

    public Candidate() {
    }

    public Candidate(Voter voter, Set<Voter> voters) {
        this.voter = voter;
        this.voters = voters;
    }

    public Candidate(Long id, Set<Voter> voters) {
        this.id = id;
        this.voters = voters;
    }

    public Set<Voter> getVoters() {
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

