package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * Modelattribute实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class ModelattributeEntity implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 字段名
     */
    private String name;

    /**
     * 数据库字段名
     */
    private String dbname;

    /**
     * 备注
     */
    private String alies;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 所属用户
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
