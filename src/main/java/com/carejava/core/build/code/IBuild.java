/*
 * Copyright (C), 2015-2018, zhenhai.xiong
 * FileName: IBuild.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2018年02月09日 14:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.core.build.code;

import com.carejava.core.build.model.Project;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2018年02月09日]
 */
public interface IBuild {

    /**
     * 构建项目
     * @param project 项目对象
     * @param rootPath 构建项目目标地址
     */
    void build(Project project, String rootPath);
}
