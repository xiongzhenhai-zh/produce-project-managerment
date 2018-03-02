package com.carejava.core.build.model;

import java.util.List;

public class Navbar {
    public static final int TYPE_PAGE = 1;
    public static final int TYPE_PANEL = 2;

    private String id;

    private String name;

    private String alies;

    private int type;

    private String page;

    private List<Navbar> children;

    public Navbar() {
        super();
    }

    public Navbar(String id, String name, String alies, int type, String page) {
        super();
        this.id = id;
        this.name = name;
        this.alies = alies;
        this.type = type;
        this.page = page;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Navbar> getChildren() {
        return children;
    }

    public void setChildren(List<Navbar> children) {
        this.children = children;
    }
}
