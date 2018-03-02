/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: LoginFilter.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2017年12月06日 15:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.duce.ject.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * 登录过滤器<br>
 * 对需要登录才能访问的接口进行拦截
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年9月20日]
 */
public class LoginFilter implements Filter {

    /*
     * private IDubboInfoQueryBusiness dubboInfoQueryBusiness; private IDubboInfoUpdateBusiness dubboInfoUpdateBusiness;
     */

    /**
     * 不拦截的url
     */
    private String excludeUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls = filterConfig.getInitParameter("excludeUrls");
        excludeUrls = excludeUrls.replaceAll("\n", "");
        excludeUrls = excludeUrls.replaceAll("\\s*", "");

        // 初始化用户查询接口
        /*
         * ServletContext context = filterConfig.getServletContext(); ApplicationContext ctx =
         * WebApplicationContextUtils.getWebApplicationContext(context); dubboInfoQueryBusiness =
         * (IDubboInfoQueryBusiness) ctx.getBean("dubboInfoQueryBusiness"); dubboInfoUpdateBusiness =
         * (IDubboInfoUpdateBusiness) ctx.getBean("dubboInfoUpdateBusiness");
         */
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
            throws IOException, ServletException {
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 如果不需要做登陆验证，则直接继续访问
        if (!StringUtils.isBlank(excludeUrls)) {
            // 获取请求uri
            String uri = request.getRequestURI();
            // 判断是否需要做登陆验证
            if (matchExUrls(uri)) {
                fc.doFilter(request, response);
                return;
            }
        }
        Long userId = UserSessionUtil.getUserId(request);
        if (userId == null) {
            String codeValue = ErrorCode.FAIL_NOT_LOGIN.getErrorCode();
            ResultBean r = new ResultBean(codeValue, ErrorCode.FAIL_NOT_LOGIN.getErrorDesc(), null, null);
            /*response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(r));*/
            response.sendRedirect("./index.html");
        } else if (!authrizedUser(request)) {
            String codeValue = ErrorCode.NO_AUTHORITY_ERR.getErrorCode();
            ResultBean r = new ResultBean(codeValue, ErrorCode.NO_AUTHORITY_ERR.getErrorDesc(), null, null);
            response.sendRedirect("./index.html");
        } else {
            // 继续请求
            fc.doFilter(request, response);
        }
    }

    /**
     * 认证是否有权限访问接口
     * @param request
     * @return
     */
    private boolean authrizedUser(HttpServletRequest request) {
        /*List<String> privs = UserSessionUtil.getPriv(request);
        for (String priv : privs) {
            if (request.getRequestURI().indexOf(priv) > 0) {
                return true;
            }
        }
        return false;*/
        return true;
    }

    @Override
    public void destroy() {
    }

    /**
     *
     * 功能描述: <br>
     * 判断是否需要做登陆验证
     *
     * @version [V1.0, 2016年8月23日]
     * @param uri
     * @return
     */
    private Boolean matchExUrls(String uri) {
        PathMatcher matcher = new AntPathMatcher();
        for (String exUrls : excludeUrls.split(",")) {
            if (matcher.match(exUrls, uri)) {
                return true;
            }
        }
        return false;
    }
}

