package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * ProduceRecord vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2018年01月31日]
 */
public class ProduceRecordVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    private Long projectId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 系统类型
     */
    private Integer sysType;

    /**
     * 创建类型
     */
    private Date createTime;

    /**
     * 每页条数,默认10条
     */
    private Integer pageNum;

    /**
     * 起始位置
     */
    private Integer offset;

    /**
     * 当前页,默认查询第一页
     */
    private Integer currentPage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
