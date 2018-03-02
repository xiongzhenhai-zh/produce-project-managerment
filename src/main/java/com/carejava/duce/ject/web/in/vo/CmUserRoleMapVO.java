package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmUserRoleMap vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmUserRoleMapVO{

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 角色编号
     */
    private Integer roleId;

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


     public Long getId() {
            return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Integer getUserId() {
            return userId;
     }

     public void setUserId(Integer userId) {
         this.userId = userId;
     }

     public Integer getRoleId() {
            return roleId;
     }

     public void setRoleId(Integer roleId) {
         this.roleId = roleId;
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
