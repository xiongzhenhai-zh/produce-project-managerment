package com.carejava.core.build.model;

public class PanelPerporty {
    private String id;

    private String name;

    private String alies;

    private String type;

    private String template;

    private String data;

    public PanelPerporty() {
        super();
    }

    public PanelPerporty(String id, String name, String alies, String type, String template) {
        super();
        this.id = id;
        this.name = name;
        this.alies = alies;
        this.type = type;
        this.template = template;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
