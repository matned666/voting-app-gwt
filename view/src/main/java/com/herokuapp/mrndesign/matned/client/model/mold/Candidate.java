package com.herokuapp.mrndesign.matned.client.model.mold;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Mirror View object for CandidateDTO on a server side
 */
public class Candidate implements Serializable {

    private Long id;
    private Long voterId;
    private Set<Long> listOfVotesIds;

    public Candidate() {
    }

    public Candidate(Long voterId, Set<Long> listOfVotesIds) {
        this.voterId = voterId;
        this.listOfVotesIds = listOfVotesIds;
    }

    public Candidate(Long id, Long voterId, Set<Long> listOfVotesIds) {
        this.id = id;
        this.voterId = voterId;
        this.listOfVotesIds = listOfVotesIds;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Set<Long> getListOfVotesIds() {
        return listOfVotesIds;
    }

    public void setListOfVotesIds(Set<Long> listOfVotesIds) {
        this.listOfVotesIds = listOfVotesIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id) && Objects.equals(voterId, candidate.voterId) && Objects.equals(listOfVotesIds, candidate.listOfVotesIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voterId, listOfVotesIds);
    }
}
