package com.carejava.core.build.model;

import com.carejava.core.build.code.IBuild;

import java.io.Serializable;

public class Frame implements Serializable{

    private String id;
    
    private String name;
    
    private String alies;

    private IBuild build;

    public Frame() {
    }

    public Frame(String id, String name, String alies) {
        super();
        this.id = id;
        this.name = name;
        this.alies = alies;
    }

    public Frame(String name, String alies) {
        this.name = name;
        this.alies = alies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public IBuild getBuild() {
        return build;
    }

    public void setBuild(IBuild build) {
        this.build = build;
    }
}
