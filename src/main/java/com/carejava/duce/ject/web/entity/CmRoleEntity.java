package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * CmRole实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class CmRoleEntity implements Serializable{

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


}
