package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.model.Voter;

import java.util.Objects;

public class VoterDTO {

    private Long id;
    private String name;
    private String surname;
    private Long voteCandidateId;


    public static VoterDTO apply(Voter voter) {
        VoterDTO dto = new VoterDTO(voter.getName(), voter.getSurname(),
                voter.getVote() != null
                        ? voter.getVote().getId()
                        : null);
        dto.id = voter.getId();
        return dto;
    }

    public static Voter applyNew(VoterDTO dto) {
        return new Voter(dto.getName(), dto.getSurname());
    }

    public VoterDTO() {
    }

    public VoterDTO(String name, String surname, Long voteCandidateId) {
        this.name = name;
        this.surname = surname;
        this.voteCandidateId = voteCandidateId;
    }

    public VoterDTO(Long id, String name, String surname, Long voteCandidateId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.voteCandidateId = voteCandidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public Long getVoteCandidateId() {
        return voteCandidateId;
    }

    public void setVoteCandidateId(Long voteCandidateId) {
        this.voteCandidateId = voteCandidateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoterDTO)) return false;
        VoterDTO voterDTO = (VoterDTO) o;
        return Objects.equals(id, voterDTO.id) && Objects.equals(name, voterDTO.name) && Objects.equals(surname, voterDTO.surname) && Objects.equals(voteCandidateId, voterDTO.voteCandidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, voteCandidateId);
    }
}
