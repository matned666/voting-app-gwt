package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.dto.audit.AuditDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Vote;
import com.herokuapp.mrndesign.matned.model.Voter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateDTO {

    private Long voterId;

    private List<Long> listOfVotesIds;

    private AuditDTO auditDTO;

    public static CandidateDTO apply(Candidate entity) {
        CandidateDTO dto = new CandidateDTO(entity.getVoter().getId(),
                entity.getVotes()
                        .stream()
                        .map(AbstractPersistable::getId)
                        .collect(Collectors.toList()));
        dto.auditDTO = AuditDTO.apply(entity);
        return dto;
    }

    public static Candidate applyNew(Voter v, List<Vote> votes) {
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

    public AuditDTO getAuditDTO() {
        return auditDTO;
    }
}
