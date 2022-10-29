package com.herokuapp.mrndesign.matned.model;

import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.model.audit.AuditInterface;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "VOTER_ENTITY")
public class Voter extends BaseEntity implements AuditInterface {

    public static final String ROLE_NOT_FOUND = "Role not found";


    private String name;
    private String surname;

    public Voter() {
    }

    public Voter(String name, String surname) {
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

}

