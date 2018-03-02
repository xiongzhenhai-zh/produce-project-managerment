package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmUserRoleMap实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmUserRoleMapEntity implements Serializable{

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


}
