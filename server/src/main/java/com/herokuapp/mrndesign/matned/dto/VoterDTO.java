package com.herokuapp.mrndesign.matned.dto;

import com.herokuapp.mrndesign.matned.dto.audit.AuditDTO;
import com.herokuapp.mrndesign.matned.model.Voter;

public class VoterDTO {

    private String name;
    private String surname;

    private AuditDTO auditDTO;

    public static VoterDTO apply(Voter voter) {
        VoterDTO dto = new VoterDTO(voter.getName(), voter.getSurname());
        dto.auditDTO = AuditDTO.apply(voter);
        return dto;
    }

    public static Voter applyNew(VoterDTO dto) {
        return new Voter(dto.getName(), dto.getSurname());
    }

    public VoterDTO() {
    }

    public VoterDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public AuditDTO getAuditDTO() {
        return auditDTO;
    }

}
