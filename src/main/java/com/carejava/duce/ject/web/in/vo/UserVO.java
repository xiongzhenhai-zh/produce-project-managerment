package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * User vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
public class UserVO{

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
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

     public String getUserName() {
            return userName;
     }

     public void setUserName(String userName) {
         this.userName = userName;
     }

     public String getPassword() {
            return password;
     }

     public void setPassword(String password) {
         this.password = password;
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


}
