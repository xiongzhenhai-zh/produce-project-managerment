package com.carejava.core.build.code.service;

import com.carejava.core.build.code.IBuild;
import com.carejava.core.build.model.Model;
import com.carejava.core.build.model.ModelAttribute;
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
 * @author zhenhai.xiong
 * @version [V1.0, 2017年10月24日]
 */
@Component("defaultServer")
public class DefaultServer implements IBuild {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultServer.class);

    private ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
            "template/server/mime");

    private Configuration cfg;

    private GroupTemplate gt;

    public void setResourceLoader(ClasspathResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void build(Project projectTemp, String rootPath) {
        try {
            cfg = Configuration.defaultConfiguration();
            gt = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initPom(rootPath, projectTemp);

        rootPath += "src/";

        // 初始构建资源文件
        initResource(rootPath, projectTemp);

        // 初始化构建mapper
        initMapper(rootPath + "main/resources", projectTemp);

        // 初始化构建java文件
        initServer(rootPath + "main/java", projectTemp);

        // 初始化构建静态java文件
        initStatic(rootPath + "main/java", projectTemp);

        // 初始化构建web.xml文件
        initWeb(rootPath + "main/webapp/WEB-INF/", projectTemp);
    }

    private void initStatic(String rootPath, Project project) {
        String srcBasePath = rootPath + "/" + project.getPkgName().replace(".", "/");
        String corePath = srcBasePath + "/core/";
        Util.createFolders(corePath);
        Template core = gt.getTemplate("/core.beetl");
        core.binding("project", project);
        Util.createFile(corePath, "HibernateAwareObjectMapper.java", core.render());

        Template resultBean = gt.getTemplate("/resultBean.beetl");
        resultBean.binding("project", project);
        Util.createFile(corePath, "ResultBean.java", resultBean.render());

        Template pageResultBean = gt.getTemplate("/pageResultBean.beetl");
        pageResultBean.binding("project", project);
        Util.createFile(corePath, "PageResultBean.java", pageResultBean.render());

        String enumPath = srcBasePath + "/enums/";
        Util.createFolders(enumPath);
        Template enums = gt.getTemplate("/enums.beetl");
        enums.binding("project", project);
        Util.createFile(enumPath, "ErrorCode.java", enums.render());

        String businessPath = srcBasePath + "/business/";
        String businessImplPath = businessPath + "impl/";
        Template baseBusiness = gt.getTemplate("/baseBusiness.beetl");
        baseBusiness.binding("project", project);
        Util.createFile(businessImplPath, "BaseBusiness.java", baseBusiness.render());

        String baseControllerPath = srcBasePath + "/controller/";
        Template baseController = gt.getTemplate("/baseController.beetl");
        baseController.binding("project", project);
        Util.createFile(baseControllerPath, "BaseController.java", baseController.render());
    }

    private void initWeb(String rootPath, Project project) {
        Util.createFolders(rootPath);
        // 创建application.xml
        Template web = gt.getTemplate("/web.beetl");
        web.binding("imerlgo", project);
        Util.createFile(rootPath, "web.xml", web.render());
    }

    private void initPom(String rootPath, Project project) {
        Util.createFolders(rootPath);
        // 创建application.xml
        Template pom = gt.getTemplate("/pom.beetl");
        pom.binding("imerlgo", project);
        Util.createFile(rootPath, "pom.xml", pom.render());
    }

    private void initResource(String rootPath, Project project) {
        String resourcePath = rootPath + "main/resources/";
        Util.createFolders(resourcePath);
        // 创建servlet.xml
        Template servlet = gt.getTemplate("/servlet.beetl");
        servlet.binding("project", project);
        Util.createFile(resourcePath, project.getArtifactId() + "-servlet.xml", servlet.render());

        // 创建sql
        Template sql = gt.getTemplate("/sql.beetl");
        sql.binding("imerlgo", project);
        Util.createFile(resourcePath, "createDB.sql", sql.render());

        // 创建mybatis.xml
        Template mybatis = gt.getTemplate("/mybatis.beetl");
        mybatis.binding("project", project);
        Util.createFile(resourcePath, "mybatis-config.xml", mybatis.render());

        String applicationRootPath = rootPath + "main/resources/application/";
        Util.createFolders(applicationRootPath);
        // 创建application.xml
        Template applicationContext = gt.getTemplate("/applicationContext.beetl");
        applicationContext.binding("project", project);
        Util.createFile(applicationRootPath, "applicationContext.xml", applicationContext.render());

        // 创建application-aopTx.xml
        Template applicationAopTx = gt.getTemplate("/applicationContext-aopTx.beetl");
        applicationAopTx.binding("project", project);
        Util.createFile(applicationRootPath, "applicationContext-aopTx.xml", applicationAopTx.render());

        // 创建application-dao.xml
        Template applicationDao = gt.getTemplate("/applicationContext-dao.beetl");
        applicationDao.binding("project", project);
        Util.createFile(applicationRootPath, "applicationContext-dao.xml", applicationDao.render());

    }

    private void initServer(String rootPath, Project project) {
        String srcBasePath = rootPath + "/" + project.getPkgName().replace(".", "/");
        String controllerPath = srcBasePath + "/controller/";
        Util.createFolders(controllerPath);
        String servicePath = srcBasePath + "/service/";
        String serviceImplPath = servicePath + "impl/";
        Util.createFolders(serviceImplPath);
        String businessPath = srcBasePath + "/business/";
        String businessImplPath = businessPath + "impl/";
        Util.createFolders(businessImplPath);
        String daoPath = srcBasePath + "/dao/";
        Util.createFolders(daoPath);
        String entityPath = srcBasePath + "/entity/";
        Util.createFolders(entityPath);
        String inVoPath = srcBasePath + "/in/vo/";
        Util.createFolders(inVoPath);
        for (Model model : project.getModels()) {
            //新建controller文件
            Template controller = gt.getTemplate("/controller.beetl");
            controller.binding("model_xzh", model);
            controller.binding("project", project);
            Util.createFile(controllerPath, model.getName() + "Controller.java", controller.render());

            //business
            Template business = gt.getTemplate("/businessImpl.beetl");
            business.binding("model_xzh", model);
            business.binding("project", project);
            Util.createFile(businessImplPath, model.getName() + "BusinessImpl.java", business.render());

            //businessImpl
            Template businessImpl = gt.getTemplate("/business.beetl");
            businessImpl.binding("model_xzh", model);
            businessImpl.binding("project", project);
            Util.createFile(businessPath, "I" + model.getName() + "Business.java", businessImpl.render());

            //service
            Template service = gt.getTemplate("/serviceImpl.beetl");
            service.binding("model_xzh", model);
            service.binding("project", project);
            Util.createFile(serviceImplPath, model.getName() + "ServiceImpl.java", service.render());

            //serviceImpl
            Template serviceImpl = gt.getTemplate("/service.beetl");
            serviceImpl.binding("model_xzh", model);
            serviceImpl.binding("project", project);
            Util.createFile(servicePath, "I" + model.getName() + "Service.java", serviceImpl.render());

            //dao
            Template dao = gt.getTemplate("/dao.beetl");
            dao.binding("model_xzh", model);
            dao.binding("project", project);
            Util.createFile(daoPath, model.getName() + "Mapper.java", dao.render());

            //entityPath
            Template entity = gt.getTemplate("/entity.beetl");
            entity.binding("model_xzh", model);
            entity.binding("project", project);
            Util.createFile(entityPath, model.getName() + "Entity.java", entity.render());

            // ov
            Template vo = gt.getTemplate("/inVO.beetl");
            model.addAttribute(new ModelAttribute(Util.getId(), "pageNum", "每页条数,默认10条", "Integer"));
            model.addAttribute(new ModelAttribute(Util.getId(), "offset", "起始位置", "Integer"));
            model.addAttribute(new ModelAttribute(Util.getId(), "currentPage", "当前页,默认查询第一页", "Integer"));
            vo.binding("model_xzh", model);
            vo.binding("project", project);
            Util.createFile(inVoPath, model.getName() + "VO.java", vo.render());

        }

    }


    public void initMapper(String rootPath, Project project) {
        String old1 = SystemUtil.getProjectRootPath() + "WEB-INF/classes/log4j.properties";
        Util.copyFile(old1, rootPath + "/log4j.properties");

        String maperPath = rootPath + "/mapper/";
        Util.createFolders(maperPath);
        for (Model model : project.getModels()) {
            Template mapper = gt.getTemplate("/mapper.beetl");
            mapper.binding("model_xzh", model);
            mapper.binding("project", project);
            Util.createFile(maperPath, model.getName() + "Mapper.xml", mapper.render());
        }
    }
}
