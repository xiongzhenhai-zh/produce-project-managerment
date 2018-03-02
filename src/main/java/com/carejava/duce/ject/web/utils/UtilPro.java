/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: Utils.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2017年12月13日 18:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.duce.ject.web.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年12月13日]
 */
public class UtilPro {

    public static final Pattern p  = Pattern.compile("^[a-zA-Z0-9_]{1,64}$");
    /**
     * 获取数据库like,左右两边
     * @param str
     * @return
     */
    public static String getStringDBLike(String str) {
        if (StringUtils.isNotBlank(str)) {
            return "%" + str + "%";
        }
        return null;
    }
}
