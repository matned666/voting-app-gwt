package com.herokuapp.mrndesign.matned.service.exception;

public class VoterNotFoundException extends RuntimeException{

    public VoterNotFoundException() {
        super("User not found");
    }
}
