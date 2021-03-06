package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * Project vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class ProjectVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * maven坐标
     */
    private String groupid;

    /**
     * Maven项目名
     */
    private String artifactid;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目中文名
     */
    private String alies;

    /**
     * 数据库
     */
    private String databasename;

    /**
     * 包路径
     */
    private String pkgname;

    /**
     * 作者
     */
    private String autor;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 包含表
     */
    private String exclusionsTable;

    /**
     * 生成方式
     */
    private Integer produceType;

    /**
     * 数据库连接
     */
    private String dbUrl;

    /**
     * 数据库用户名
     */
    private String dbUsername;

    /**
     * 数据库密码
     */
    private String dbPassword;

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

    private String modelIds;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 后端框架类型
     */
    private String serverFrameType;

    /**
     * 前端框架类型
     */
    private String clientFrameType;

    /**
     * 移动端框架类型
     */
    private String mobileFrameType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getArtifactid() {
        return artifactid;
    }

    public void setArtifactid(String artifactid) {
        this.artifactid = artifactid;
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

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExclusionsTable() {
        return exclusionsTable;
    }

    public void setExclusionsTable(String exclusionsTable) {
        this.exclusionsTable = exclusionsTable;
    }

    public Integer getProduceType() {
        return produceType;
    }

    public void setProduceType(Integer produceType) {
        this.produceType = produceType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
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

    public String getModelIds() {
        return modelIds;
    }

    public void setModelIds(String modelIds) {
        this.modelIds = modelIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServerFrameType() {
        return serverFrameType;
    }

    public void setServerFrameType(String serverFrameType) {
        this.serverFrameType = serverFrameType;
    }

    public String getClientFrameType() {
        return clientFrameType;
    }

    public void setClientFrameType(String clientFrameType) {
        this.clientFrameType = clientFrameType;
    }

    public String getMobileFrameType() {
        return mobileFrameType;
    }

    public void setMobileFrameType(String mobileFrameType) {
        this.mobileFrameType = mobileFrameType;
    }
}
