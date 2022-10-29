package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.dto.audit.AuditDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Vote;
import com.herokuapp.mrndesign.matned.model.Voter;

public class VoteDTO {

    private Long candidateId;

    private Long voterId;

    private AuditDTO auditDTO;

    public static VoteDTO apply(Vote entity) {
        VoteDTO dto = new VoteDTO(entity.getCandidate().getId(), entity.getVoter().getId());
        dto.auditDTO = AuditDTO.apply(entity);
        return dto;
    }

    public static Vote applyNew(Voter v, Candidate c) {
        return new Vote(c, v);
    }

    public VoteDTO() {
    }

    public VoteDTO(Long candidateId, Long voterId) {
        this.candidateId = candidateId;
        this.voterId = voterId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public AuditDTO getAuditDTO() {
        return auditDTO;
    }

}
