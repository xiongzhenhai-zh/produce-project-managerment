package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmRolePrivMap实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmRolePrivMapEntity implements Serializable{

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 权限编号
     */
    private Integer privId;


     public Long getId() {
            return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Integer getRoleId() {
            return roleId;
     }

     public void setRoleId(Integer roleId) {
         this.roleId = roleId;
     }

     public Integer getPrivId() {
            return privId;
     }

     public void setPrivId(Integer privId) {
         this.privId = privId;
     }


}
