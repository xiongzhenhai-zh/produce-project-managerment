package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * Model vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class ModelVO {

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
     * 每页条数,默认10条
     */
    private Integer pageNum = 10;

    /**
     * 起始位置
     */
    private Integer offset;

    /**
     * 当前页,默认查询第一页
     */
    private Integer currentPage = 1;

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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
