/*
 * Copyright (C), 2015-2017, zhenhai.xiong
 * FileName: test.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2017年12月13日 15:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.test;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年12月13日]
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Test test = new Test();
        test.fileLoad();
        test.classLoad();
    }

    private void fileLoad() throws Exception{
        String root = System.getProperty("user.dir")+ File.separator+"template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/hello.txt");
        String str = t.render();
        System.out.println(str);
    }

    private void classLoad() throws Exception{
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("org/beetl/sample/s01/");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/hello.txt");
        String str = t.render();
        System.out.println(str);
    }
}
