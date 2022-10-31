package com.herokuapp.mrndesign.matned.client.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class Voter implements Serializable {

    private Long id;
    private String name;
    private String surname;

    public static Voter apply(VoterJS js) {
        return new Voter(js.getId(), js.getName(), js.getSurname());
    }

    public Voter() {
    }

    public Voter(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Voter(Long id, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter)) return false;
        Voter voter = (Voter) o;
        return Objects.equals(id, voter.id) && Objects.equals(name, voter.name) && Objects.equals(surname, voter.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":" + "\"" + name + "\"" +
                ",\"surname\":" + "\"" + surname + "\"" +
                '}';
    }
}
