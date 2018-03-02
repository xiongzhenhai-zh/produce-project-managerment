package com.carejava.duce.ject.web.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * Model实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class ModelEntity implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 实体名
     */
    private String name;

    /**
     * 对应数据表名
     */
    private String dbname;

    /**
     * 备注
     */
    private String alies;

    /**
     * id类型
     */
    private String idtype;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 字段Id，逗号隔开
     */
    private String attributeIds;

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

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAttributeIds() {
        return attributeIds;
    }

    public void setAttributeIds(String attributeIds) {
        this.attributeIds = attributeIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
