package com.herokuapp.mrndesign.matned.client.model.dto;

import com.herokuapp.mrndesign.matned.client.model.dto.audit.AuditDTO;

import java.util.List;
import java.util.Objects;

public class Candidate implements Listable {

    private Long voterId;

    private List<Long> listOfVotesIds;

    private AuditDTO auditDTO;

    public Candidate() {
    }

    public Candidate(Long voterId, List<Long> listOfVotesIds) {
        this.voterId = voterId;
        this.listOfVotesIds = listOfVotesIds;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public List<Long> getListOfVotesIds() {
        return listOfVotesIds;
    }

    public void setListOfVotesIds(List<Long> listOfVotesIds) {
        this.listOfVotesIds = listOfVotesIds;
    }

    public AuditDTO getAuditDTO() {
        return auditDTO;
    }

    @Override
    public Long getId() {
        return voterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(voterId, candidate.voterId) && Objects.equals(listOfVotesIds, candidate.listOfVotesIds) && Objects.equals(auditDTO, candidate.auditDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterId, listOfVotesIds, auditDTO);
    }
}
