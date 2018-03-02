package com.carejava.duce.ject.web.in.vo;

import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmPriv vo类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmPrivVO{

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父权限编号
     */
    private Integer pid;

    /**
     * 权限类别
     */
    private String category;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单展开状态 
     */
    private String menuStat;

    /**
     * 菜单显示顺序
     */
    private Integer menuSort;

    /**
     * 指向的URL
     */
    private String openUrl;

    /**
     * 按钮的标识
     */
    private String identifyCode;

    /**
     * 备选字段
     */
    private String reamrk;

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

     public Integer getPid() {
            return pid;
     }

     public void setPid(Integer pid) {
         this.pid = pid;
     }

     public String getCategory() {
            return category;
     }

     public void setCategory(String category) {
         this.category = category;
     }

     public String getMenuIcon() {
            return menuIcon;
     }

     public void setMenuIcon(String menuIcon) {
         this.menuIcon = menuIcon;
     }

     public String getMenuStat() {
            return menuStat;
     }

     public void setMenuStat(String menuStat) {
         this.menuStat = menuStat;
     }

     public Integer getMenuSort() {
            return menuSort;
     }

     public void setMenuSort(Integer menuSort) {
         this.menuSort = menuSort;
     }

     public String getOpenUrl() {
            return openUrl;
     }

     public void setOpenUrl(String openUrl) {
         this.openUrl = openUrl;
     }

     public String getIdentifyCode() {
            return identifyCode;
     }

     public void setIdentifyCode(String identifyCode) {
         this.identifyCode = identifyCode;
     }

     public String getReamrk() {
            return reamrk;
     }

     public void setReamrk(String reamrk) {
         this.reamrk = reamrk;
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
