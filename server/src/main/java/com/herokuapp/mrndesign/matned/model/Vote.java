package com.herokuapp.mrndesign.matned.model;

import com.herokuapp.mrndesign.matned.model.audit.AuditInterface;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "VOTE")
public class Vote extends BaseEntity implements AuditInterface {

    @OneToOne
    private Voter voter;

    @OneToOne
    private Candidate candidate;


    public Voter getVoter() {
        return voter;
    }

    public Vote(Candidate candidate, Voter voter) {
        this.voter = voter;
        this.candidate = candidate;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
