package com.herokuapp.mrndesign.matned.client.model.dto;

import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative = true, namespace = GLOBAL)
public class VoterJS {

    private Long id;
    private String name;
    private String surname;

    public VoterJS() {
    }

    public native String getName();

    public native void setName(String name);

    public native String getSurname();

    public native void setSurname(String surname);

    public native void setId(Long id);

    public native Long getId();

}
