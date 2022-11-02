package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;

import java.util.Set;
import java.util.stream.Collectors;

public class CandidateDTO {

    private Long id;

    private Long voterId;

    private Set<Long> listOfVotesIds;

    public static CandidateDTO apply(Candidate entity) {
        CandidateDTO dto = new CandidateDTO(entity.getVoter().getId(),
                entity.getVoters()
                        .stream()
                        .map(Voter::getId)
                        .collect(Collectors.toSet()));
        dto.id = entity.getId();
        return dto;
    }

    public static Candidate applyNew(Voter v, Set<Voter> votes) {
        return new Candidate(v, votes);
    }

    public CandidateDTO() {
    }

    public CandidateDTO(Long voterId, Set<Long> listOfVotesIds) {
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
}
