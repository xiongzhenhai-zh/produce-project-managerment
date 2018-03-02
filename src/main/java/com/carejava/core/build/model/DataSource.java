package com.carejava.core.build.model;

public class DataSource {

    private String id;

    private String name;

    private String type;

    private String url;

    private String username;

    private String password;

    public DataSource() {
        super();
    }

    public DataSource(String id, String name, String type, String url, String username, String password) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.username = username;
        this.password = password;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
