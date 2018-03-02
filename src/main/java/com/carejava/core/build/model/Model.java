package com.carejava.core.build.model;

import java.util.ArrayList;
import java.util.List;

import com.carejava.core.build.utils.Util;
import org.apache.commons.lang3.StringUtils;

public class Model {
    
    private String id;

    private String name;

    private String dbName;
    
    private String lowName;

    private String alies;

    private String idType;

    private List<ModelAttribute> attributes;

    private List<Function> functions;

    private List<Relation> relations;
    
    public Model() {
        super();
        attributes = new ArrayList<ModelAttribute>();
        functions = new ArrayList<Function>();
        relations = new ArrayList<Relation>();
    }
    
    public Model(String id, String name, String alies, String idType, String dbName) {
        super();
        this.id = id;
        this.name = name;
        setLowName(Util.toLowerCaseFirstOne(name));
        setDbName(Util.toUpperCase(Util.toLowerCaseFirstOne(name)));
        if (StringUtils.isNotBlank(dbName)) {
            this.dbName = dbName;
        }
        this.alies = alies;
        this.idType = idType;
        attributes = new ArrayList<ModelAttribute>();
        functions = new ArrayList<Function>();
        relations = new ArrayList<Relation>();
    }

    public Model(String id, String name, String alies, String idType, Project project) {
        super();
        this.id = id;
        this.name = name;
        setLowName(Util.toLowerCaseFirstOne(name));
        setDbName(Util.toUpperCase(Util.toLowerCaseFirstOne(name)));
        this.alies = alies;
        this.idType = idType;
        attributes = new ArrayList<ModelAttribute>();
        functions = new ArrayList<Function>();
        relations = new ArrayList<Relation>();
    }

    public String getLowName(){
        return Util.toLowerCaseFirstOne(this.name);
    }
    
    public void addAttribute(ModelAttribute attr) {
        if(attributes==null){
            attributes = new ArrayList<ModelAttribute>();
        }
        attributes.add(attr);
    }

    public void addRelation(Relation relation) {
        if(relations==null){
            relations = new ArrayList<Relation>();
        }
        relations.add(relation);
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
        setLowName(Util.toLowerCaseFirstOne(name));
        setDbName(Util.toUpperCase(Util.toLowerCaseFirstOne(name)));
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public List<ModelAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ModelAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public void setLowName(String lowName) {
        this.lowName = lowName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
