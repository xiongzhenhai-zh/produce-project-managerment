package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.enums.ErrorCode;

/**
 * 业务层基类<br>
 * 封装业务层一些公共方法
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class BaseBusiness {

    /**
     * 日志
     */
    protected final static Logger LOGGER = LoggerFactory.getLogger(BaseBusiness.class);

    /**
     * 
     * 功能描述: <br>
     * 获取resultBean
     *
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
        if (null != code) {
            result = new ResultBean(code.getErrorCode(), code.getErrorDesc(), accessToken, content);
        }
        return result;
    }
}
