package com.carejava.duce.ject.web.controller;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.enums.ErrorCode;

/**
 * 控制器基类<br>
 * 提供公共方法
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class BaseController {

    /**
     * 
     * 功能描述: <br>
     * 获取resultBean
     *
     * @param code 结果码
     * @return 处理结果
     */
    protected ResultBean getResultBean(ErrorCode code) {
        return getResultBean(code, null, null);
    }

    /**
     * 
     * 功能描述: <br>
     * 获取resultBean
     *
     * @param code 结果码
     * @param accessToken 用户token
     * @return 处理结果
     */
    protected ResultBean getResultBean(ErrorCode code, String accessToken) {
        return getResultBean(code, accessToken, null);
    }

    /**
     * 
     * 功能描述: <br>
     * 获取resultBean
     *
     * @param code 结果码
     * @param content 返回内容
     * @return 处理结果
     */
    protected ResultBean getResultBean(ErrorCode code, Serializable content) {
        return getResultBean(code, null, content);
    }

    /**
     * 
     * 功能描述: <br>
     * 获取resultBean
     *
     * @param code 结果码
     * @param accessToken 用户token
     * @param content 返回内容
     * @return 处理结果
     */
    protected ResultBean getResultBean(ErrorCode code, String accessToken, Serializable content) {
        ResultBean result = null;
        String codeValue = code.getErrorCode();
        if (StringUtils.isNotBlank(codeValue)) {
            result = new ResultBean(codeValue, code.getErrorDesc(), accessToken, content);
        }
        return result;
    }
}
