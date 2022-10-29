package com.herokuapp.mrndesign.matned.model;

import com.herokuapp.mrndesign.matned.model.audit.AuditInterface;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CANDIDATE_ENTITY")
public class Candidate extends BaseEntity implements AuditInterface {

    public static final String ROLE_NOT_FOUND = "Role not found";

    @OneToOne
    private Voter voter;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> votes;

    public Candidate() {
    }

    public Candidate(Voter voter, List<Vote> votes) {
        this.voter = voter;
        this.votes = votes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}

