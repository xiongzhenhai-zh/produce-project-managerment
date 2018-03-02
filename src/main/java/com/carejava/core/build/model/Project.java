package com.carejava.core.build.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Project {

    private String id;
    
    private String groupId;
    
    private String artifactId;
    
    private String name;
    
    private String alies;

    private String databaseName;
    
    private DataSource dataSource;
    
    private String pkgName;
    
    private String autor;
    
    private List<Model> models;
    
    private Frame serverFrame;

    private Frame clientFrame;

    private Frame mobileFrame;

    private List<Navbar> navbars;

    private List<Page> pages;
    
    private String dateString = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    
    public Project(String id, String groupId, String artifactId, String name, String alies, Frame serverFrame,
            Frame clientFrame, Frame mobileFrame) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.alies = alies;
        this.serverFrame = serverFrame;
        this.clientFrame = clientFrame;
        this.mobileFrame = mobileFrame;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Frame getServerFrame() {
        return serverFrame;
    }

    public void setServerFrame(Frame serverFrame) {
        this.serverFrame = serverFrame;
    }

    public Frame getClientFrame() {
        return clientFrame;
    }

    public void setClientFrame(Frame clientFrame) {
        this.clientFrame = clientFrame;
    }

    public Frame getMobileFrame() {
        return mobileFrame;
    }

    public void setMobileFrame(Frame mobileFrame) {
        this.mobileFrame = mobileFrame;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public List<Navbar> getNavbars() {
        return navbars;
    }

    public void setNavbars(List<Navbar> navbars) {
        this.navbars = navbars;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}

