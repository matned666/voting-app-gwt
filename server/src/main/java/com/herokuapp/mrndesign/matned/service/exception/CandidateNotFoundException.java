package com.herokuapp.mrndesign.matned.service.exception;

public class CandidateNotFoundException extends RuntimeException{

    public CandidateNotFoundException() {
        super("Candidate not found");
    }
}
