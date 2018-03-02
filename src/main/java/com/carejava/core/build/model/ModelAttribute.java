package com.carejava.core.build.model;

import com.carejava.core.build.utils.Util;
import org.apache.commons.lang3.StringUtils;

public class ModelAttribute {

    private String id;

    private String name;

    private String firstUpName;

    private String dbName;

    private String alies;

    private String type;

    private boolean columnKey;

    public ModelAttribute(String id, String name, String alies, String type, String dbName, boolean columnKey) {
        super();
        this.id = id;
        this.name = name;
        this.firstUpName = Util.toUpperCaseFirstOne(name);
        setDbName(Util.toUpperCase(name));
        if (StringUtils.isNotBlank(dbName)){
            this.dbName = dbName;
        }
        this.alies = alies;
        this.type = type;
        this.columnKey = columnKey;
    }

    public ModelAttribute(String id, String name, String alies, String type) {
        super();
        this.id = id;
        this.name = name;
        this.firstUpName = Util.toUpperCaseFirstOne(name);
        setDbName(Util.toUpperCase(name));
        this.alies = alies;
        this.type = type;
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
        this.firstUpName = Util.toUpperCaseFirstOne(name);
        setDbName(Util.toUpperCase(name));
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

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getFirstUpName() {
        return firstUpName;
    }

    public void setFirstUpName(String firstUpName) {
        this.firstUpName = firstUpName;
    }

    public boolean getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(boolean columnKey) {
        this.columnKey = columnKey;
    }
}
