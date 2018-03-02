/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: UserSessionUtil.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2017年12月06日 15:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.duce.ject.web.utils;

import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 获取用户存在session 中的数据
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年9月20日]
 */
public class UserSessionUtil {

    public static Long getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Long) session.getAttribute("userId");
    }

    public static String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("getUserName");
    }

    public static Long getRoleId(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Long) session.getAttribute("roleId");
    }

    public static UserEntity getAll(HttpServletRequest request) {
        UserEntity entity = new UserEntity();
        HttpSession session = request.getSession();
        entity.setUserName((String) session.getAttribute("getUserName"));
        entity.setId((Long) session.getAttribute("userId"));
        return entity;
    }

    /**
     * 获取用户所有接口权限
     * @param request
     * @return
     */
    public static List<String> getPriv(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (List<String>) session.getAttribute("priv");
    }

    public static void setSession(HttpServletRequest request, UserEntity entity, List<CmRoleEntity> roleEntities, List<CmPrivEntity> privEntities) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", entity.getId());
        session.setAttribute("userName", entity.getUserName());
        if (roleEntities != null && roleEntities.size() == 1) {
            session.setAttribute("roleId", roleEntities.get(0).getId());
        }
        // 保存用户接口权限信息
        List<String> privs = new ArrayList<>(privEntities.size());
        for (CmPrivEntity vo: privEntities) {
            privs.add(vo.getReamrk());
        }
        session.setAttribute("priv",privs);
    }


}
