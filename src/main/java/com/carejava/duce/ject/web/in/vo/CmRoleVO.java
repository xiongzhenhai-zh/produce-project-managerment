package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmRole vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmRoleVO{

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

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

     public String getName() {
            return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getDescription() {
            return description;
     }

     public void setDescription(String description) {
         this.description = description;
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
