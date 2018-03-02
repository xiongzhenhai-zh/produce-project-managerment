package com.carejava.core.build.model;

import java.util.ArrayList;
import java.util.List;

public class Panel {

    private String id;

    private String name;

    private String alies;

    private String template;

    private boolean showTitle;

    private boolean showBorder;

    private String theme;

    private boolean fit;

    private String width;

    private String height;

    private String href;

    private Store store;

    private List<PanelPerporty> perporties;

    private List<PanelPerporty> tools;

    private List<PanelPerporty> toolbar;

    private List<PanelPerporty> footer;

    public Panel() {
        super();
    }

    public Panel(String id, String name, String alies, String template) {
        super();
        this.id = id;
        this.name = name;
        this.alies = alies;
        this.template = template;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<PanelPerporty> getPerporties() {
        return perporties;
    }

    public void setPerporties(List<PanelPerporty> perporties) {
        this.perporties = perporties;
    }

    public List<PanelPerporty> getTools() {
        return tools;
    }

    public void setTools(List<PanelPerporty> tools) {
        this.tools = tools;
    }

    public List<PanelPerporty> getToolbar() {
        return toolbar;
    }

    public void setToolbar(List<PanelPerporty> toolbar) {
        this.toolbar = toolbar;
    }

    public List<PanelPerporty> getFooter() {
        return footer;
    }

    public void setFooter(List<PanelPerporty> footer) {
        this.footer = footer;
    }

    public void addPerporty(PanelPerporty panelPerporty) {
        if (perporties == null) {
            perporties = new ArrayList<PanelPerporty>();
        }
        perporties.add(panelPerporty);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
