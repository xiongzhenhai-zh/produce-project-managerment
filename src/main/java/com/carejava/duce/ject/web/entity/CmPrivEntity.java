package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmPriv实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmPrivEntity implements Serializable{

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
    private Long pid;

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
     * 子权限列表
     */
    private List<CmPrivEntity> subPrivs;


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

     public Long getPid() {
            return pid;
     }

     public void setPid(Long pid) {
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

    public void setSubPrivs(List<CmPrivEntity> subPrivs) {
        this.subPrivs = subPrivs;
    }

    public List<CmPrivEntity> getSubPrivs() {
        return subPrivs;
    }
}
