package com.carejava.duce.ject.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 〈一句话功能简述〉<br>
 * User实体类
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月09日]
 */
public class UserEntity implements Serializable{

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


}
