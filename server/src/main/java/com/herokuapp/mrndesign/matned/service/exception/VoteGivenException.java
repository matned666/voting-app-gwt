package com.herokuapp.mrndesign.matned.service.exception;

public class VoteGivenException extends RuntimeException{

    public VoteGivenException() {
        super("The voter already has given his vote");
    }
}
