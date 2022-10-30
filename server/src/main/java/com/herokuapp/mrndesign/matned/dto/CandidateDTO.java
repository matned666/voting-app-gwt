package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateDTO {

    private Long id;

    private Long voterId;

    private List<Long> listOfVotesIds;

    public static CandidateDTO apply(Candidate entity) {
        CandidateDTO dto = new CandidateDTO(entity.getVoter().getId(),
                entity.getVoters()
                        .stream()
                        .map(Voter::getId)
                        .collect(Collectors.toList()));
        dto.id = entity.getId();
        return dto;
    }

    public static Candidate applyNew(Voter v, List<Voter> votes) {
        return new Candidate(v, votes);
    }

    public CandidateDTO() {
    }

    public CandidateDTO(Long voterId, List<Long> listOfVotesIds) {
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

    public Long getId() {
        return id;
    }
}
