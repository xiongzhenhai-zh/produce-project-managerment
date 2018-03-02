/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: ModelOutVO.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2017年11月09日 22:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.duce.ject.web.outVO;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年11月09日]
 */
public class ModelOutVO {

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

    private String attributeIds;

    List<ModelattributeOutVO> modelattributeOutVOS;

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

    public List<ModelattributeOutVO> getModelattributeOutVOS() {
        return modelattributeOutVOS;
    }

    public void setModelattributeOutVOS(List<ModelattributeOutVO> modelattributeOutVOS) {
        this.modelattributeOutVOS = modelattributeOutVOS;
    }

    public String getAttributeIds() {
        return attributeIds;
    }

    public void setAttributeIds(String attributeIds) {
        this.attributeIds = attributeIds;
    }
}
