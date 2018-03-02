/*
 * Copyright (C), 2015-2018, zhenhai.xiong
 * FileName: LayerClient.java
 * Author:   熊振海	zhenhai.xiong@mi-me.com
 * Date:     2018年02月09日 15:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.carejava.core.build.code.client;

import com.carejava.core.build.code.IBuild;
import com.carejava.core.build.model.Model;
import com.carejava.core.build.model.Project;
import com.carejava.core.build.utils.SystemUtil;
import com.carejava.core.build.utils.Util;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2018年02月09日]
 */
@Component("layerClient")
public class LayerClient implements IBuild {

    private final static Logger LOGGER = LoggerFactory.getLogger(LayerClient.class);

    private static ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
            "template/client/layui");

    private static Configuration cfg = null;

    private static GroupTemplate gt = null;

    @Override
    public void build(Project project, String rootPath) {
        try {
            cfg = Configuration.defaultConfiguration();
            gt = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // mvn 项目路径
        String mvnRootPath = rootPath + "src/main/webapp/WEB-INF/";

        // 初始化构建固定资源
        initResource(mvnRootPath);

        // 初始化构建html文件
        initHtml(mvnRootPath, project);

        // 初始化构建js文件
        initJs(mvnRootPath, project);
    }

    private void initJs(String rootPath, Project project) {
        String mainJsPath = rootPath + "/resources/js/";
        Template mainJs = gt.getTemplate("/main.beetl");
        mainJs.binding("project", project);
        Util.createFile(mainJsPath, "main.js", mainJs.render());
        for (Model model : project.getModels()) {
            String resourceJsPath = rootPath + "/resources/js/" + model.getLowName() + "/";
            Util.createFolders(resourceJsPath);
            // 新建js文件
            Template createJs = gt.getTemplate("/createJs.beetl");
            createJs.binding("model_xzh", model);
            createJs.binding("project", project);
            Util.createFile(resourceJsPath, model.getLowName() + "Create.js", createJs.render());
            //列表js
            Template listJs = gt.getTemplate("/listJs.beetl");
            listJs.binding("model_xzh", model);
            listJs.binding("project", project);
            Util.createFile(resourceJsPath, model.getLowName() + "List.js", listJs.render());
            //详情
            Template detailJs = gt.getTemplate("/detailJs.beetl");
            detailJs.binding("model_xzh", model);
            detailJs.binding("project", project);
            Util.createFile(resourceJsPath, model.getLowName() + "Detail.js", detailJs.render());

            //编辑
            Template editJs = gt.getTemplate("/editJs.beetl");
            editJs.binding("model_xzh", model);
            editJs.binding("project", project);
            Util.createFile(resourceJsPath, model.getLowName() + "Edit.js", editJs.render());
        }
    }

    private void initHtml(String rootPath, Project project) {
        for (Model model : project.getModels()) {
            String viewPath = rootPath + "/views/" + model.getLowName() + "/";
            Util.createFolders(viewPath);
            //新建html文件
            Template createHtml = gt.getTemplate("/createHtml.beetl");
            createHtml.binding("model_xzh", model);
            createHtml.binding("project", project);
            Util.createFile(viewPath, model.getLowName() + "Create.html", createHtml.render());
            //列表html
            Template listHtml = gt.getTemplate("/listHtml.beetl");
            listHtml.binding("model_xzh", model);
            listHtml.binding("project", project);
            Util.createFile(viewPath, model.getLowName() + "List.html", listHtml.render());
            //详情
            Template detailHtml = gt.getTemplate("/detailHtml.beetl");
            detailHtml.binding("model_xzh", model);
            detailHtml.binding("project", project);
            Util.createFile(viewPath, model.getLowName() + "Detail.html", detailHtml.render());
            //详情
            Template editHtml = gt.getTemplate("/editHtml.beetl");
            editHtml.binding("model_xzh", model);
            editHtml.binding("project", project);
            Util.createFile(viewPath, model.getLowName() + "Edit.html", editHtml.render());
        }
        String viewPath = rootPath + "/views/";
        Util.createFolders(viewPath);
        // 新建html文件
        Template createHtml = gt.getTemplate("/mainHtml.beetl");
        createHtml.binding("project", project);
        Util.createFile(viewPath, "index.html", createHtml.render());
    }

    private void initResource(String rootPath) {
        try {
            String old1 = SystemUtil.getProjectClassesPath() + "template/client/layui/source/";
            Util.copyFolder(old1, rootPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
