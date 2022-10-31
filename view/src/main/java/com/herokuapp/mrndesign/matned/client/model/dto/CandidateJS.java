package com.herokuapp.mrndesign.matned.client.model.dto;

import jsinterop.annotations.JsType;

import java.util.List;

import static jsinterop.annotations.JsPackage.GLOBAL;


@JsType(isNative = true, namespace = GLOBAL)
public class CandidateJS {

    private Long id;
    private Long voterId;
    private List<Long> listOfVotesIds;

    public CandidateJS() {
    }

    public native Long getVoterId();

    public native void setVoterId(Long voterId);

    public native List<Long> getListOfVotesIds();

    public native void setListOfVotesIds(List<Long> listOfVotesIds);

    public native void setId(Long id);

    public native Long getId();


}
