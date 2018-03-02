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

@Component("defaultClient")
public class DefaultClient implements IBuild {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultClient.class);

    private static ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
            "template/client/mime");

    private static Configuration cfg = null;

    private static GroupTemplate gt = null;

    /**
     * 初始化客户端生成方法: <br>
     * 〈功能详细描述〉
     *
     * @param projectTemp 项目对象
     * @param rootPath    生成文件的根目录
     * @version [V1.0, 2017年5月9日]
     */
    public void build(Project projectTemp, String rootPath) {
        try {
            cfg = Configuration.defaultConfiguration();
            gt = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            LOGGER.error("加载bettl配置文件错误");
            e.printStackTrace();
        }
        // mvn 项目路径
        String mvnRootPath = rootPath + "src/main/webapp/WEB-INF/";

        // 初始化构建固定资源
        initResource(mvnRootPath);

        // 初始化构建html文件
        initHtml(mvnRootPath, projectTemp);

        // 初始化构建js文件
        initJs(mvnRootPath, projectTemp);
    }

    /**
     * 初始化html: <br>
     * 〈功能详细描述〉
     *
     * @version [V1.0, 2017年5月8日]
     */
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
        Util.createFile(viewPath, "main.html", createHtml.render());
    }

    /**
     * 初始化js
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @version [V1.0, 2017年5月8日]
     */
    private void initJs(String rootPath, Project project) {
        //初始化main文件
        String mainJsPath = rootPath + "/resources/js/";
        Util.createFolders(mainJsPath);
        Template mainJs = gt.getTemplate("/main.beetl");
        mainJs.binding("project", project);
        Util.createFile(mainJsPath, "main.js", mainJs.render());
        for (Model model : project.getModels()) {
            String resourceJsPath = rootPath + "/resources/js/" + model.getLowName() + "/";
            Util.createFolders(resourceJsPath);
            //新建js文件
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

    /**
     * 初始化资源文件: <br>
     * 〈功能详细描述〉
     *
     * @throws IOException
     * @version [V1.0, 2017年5月8日]
     */
    private void initResource(String rootPath) {
        try {
            String old1 = SystemUtil.getProjectRootPath() + "WEB-INF/resources";
            Util.copyFolder(old1, rootPath + "/resources");
            Util.removeFolder(rootPath + "/resources/js/cmPriv");
            Util.removeFolder(rootPath + "/resources/js/cmRole");
            Util.removeFolder(rootPath + "/resources/js/cmRolePrivMap");
            Util.removeFolder(rootPath + "/resources/js/cmUserRoleMap");
            Util.removeFolder(rootPath + "/resources/js/model");
            Util.removeFolder(rootPath + "/resources/js/modelattribute");
            Util.removeFolder(rootPath + "/resources/js/project");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
