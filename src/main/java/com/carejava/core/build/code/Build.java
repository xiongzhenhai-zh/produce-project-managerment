package com.carejava.core.build.code;

import com.carejava.core.build.model.DataSource;
import com.carejava.core.build.model.Frame;
import com.carejava.core.build.model.Model;
import com.carejava.core.build.model.ModelAttribute;
import com.carejava.core.build.model.Project;
import com.carejava.core.build.utils.FileToZip;
import com.carejava.core.build.utils.InitModelFromDBUtil;
import com.carejava.core.build.utils.SystemUtil;
import com.carejava.core.build.utils.Util;
import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.outVO.ModelOutVO;
import com.carejava.duce.ject.web.outVO.ModelattributeOutVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class Build {

    private final static Logger LOGGER = LoggerFactory.getLogger(Build.class);

    private static final String BUILD_PATH = SystemUtil.isLinux() ? "~/home/code/userId_%s/%s-%s/" : "C:/code/userId_%s/%s-%s/";

    private static final String ZIP_PATH = SystemUtil.isLinux() ? "~/home/code/userId_%s/%s-src-%s.zip" : "C:/code/userId_%s/%s-src-%s.zip";

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private OSType osType;

    /**
     * 通过数据库反向生成
     *
     * @param projectEntity
     * @param userId
     * @return
     */
    public String buildByDB(ProjectEntity projectEntity, String userId) {
        LOGGER.info("通过数据库反向构建");
        Calendar calendar = Calendar.getInstance();
        String date = SDF.format(calendar.getTime());
        Project project = covertByDBEntity(projectEntity);
        List<String> exclusions = new ArrayList<>();
        if (StringUtils.isNotEmpty(projectEntity.getExclusionsTable())) {
            exclusions = Arrays.asList(projectEntity.getExclusionsTable().split(","));
        }
        InitModelFromDBUtil.init(project, exclusions);

        String rootPath = getBuildPath(userId, project.getArtifactId(), date);
        String zipPath = getZipPath(userId, project.getArtifactId(), date);

        IBuild buildClient = project.getClientFrame().getBuild();
        buildClient.build(project, rootPath);

        IBuild buildService = project.getServerFrame().getBuild();
        buildService.build(project, rootPath);

        FileToZip.createZip(rootPath, zipPath);
        Util.removeFolder(rootPath);
        LOGGER.info("构建结束");
        return zipPath;
    }

    private Project covertByDBEntity(ProjectEntity projectEntity) {
        Project project = new Project(Util.getId(), projectEntity.getGroupid(), projectEntity.getArtifactid(),
                projectEntity.getName(), projectEntity.getAlies(), getFrame("service", projectEntity.getServerFrameType()),
                getFrame("client", projectEntity.getClientFrameType()),getFrame("mobile", "mobile"));
        project.setPkgName(projectEntity.getPkgname());
        project.setDataSource(new DataSource(
                Util.getId(),
                "mysql",
                "mysql",
                projectEntity.getDbUrl(),
                projectEntity.getDbUsername(), projectEntity.getDbPassword()));
        project.setAutor(projectEntity.getAutor());
        return project;
    }

    private Frame getFrame(String type, String frameName) {
        Frame frame = null;
        try {
            switch (type) {
                case "service":
                    frame = osType.getServiceTypes().get(frameName);
                    break;
                case "client":
                    frame = osType.getClientTypes().get(frameName);
                    break;
                case "mobile":
                    // 暂未实现
//                    frame = osType.getMobileTypes().get(frameName);
                    frame = new Frame(Util.getId(), "mobile", "mobile");
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            LOGGER.error("未实现{}模板方法", frameName);
        }
        if (frame == null)
        throw new NullPointerException("未实现" + frameName + "模板方法");
        return frame;
    }

    /**
     * 功能描述: <br>
     * 通过模型生成
     *
     * @param projectEntity
     * @param modelOutVOS
     * @param userId
     * @return
     * @version [V1.0, 2017-11-06 0006]
     */
    public String buildByModel(ProjectEntity projectEntity, List<ModelOutVO> modelOutVOS, String userId) {
        LOGGER.info("自定义构建");
        Calendar calendar = Calendar.getInstance();
        String date = SDF.format(calendar.getTime());
        Project project = covertByModel(projectEntity, modelOutVOS);
        String rootPath = getBuildPath(userId, project.getArtifactId(), date);
        String zipPath = getZipPath(userId, project.getArtifactId(), date);

        IBuild buildClient = project.getClientFrame().getBuild();
        buildClient.build(project, rootPath);

        IBuild buildService = project.getServerFrame().getBuild();
        buildService.build(project, rootPath);

        FileToZip.createZip(rootPath, zipPath);
        Util.removeFolder(rootPath);
        LOGGER.info("构建结束");
        return zipPath;
    }

    private Project covertByModel(ProjectEntity projectEntity, List<ModelOutVO> modelOutVOS) {
        Project project = new Project(Util.getId(), projectEntity.getGroupid(), projectEntity.getArtifactid(), projectEntity.getName(),
                projectEntity.getAlies(), getFrame("service", projectEntity.getServerFrameType()),
                getFrame("client", projectEntity.getClientFrameType()), getFrame("mobile", "mobile"));
        project.setPkgName(projectEntity.getPkgname());
        project.setDataSource(new DataSource(
                Util.getId(),
                "mysql",
                "mysql",
                projectEntity.getDbUrl(),
                projectEntity.getDbUsername(), projectEntity.getDbPassword()));
        project.setAutor(projectEntity.getAutor());
        project.setDatabaseName(projectEntity.getDatabasename());
        project.setModels(covertModel(project, modelOutVOS));
        return project;
    }

    private List<Model> covertModel(Project project, List<ModelOutVO> modelOutVOS) {
        List<Model> models = new ArrayList<>();
        for (ModelOutVO vo : modelOutVOS) {
            Model model = new Model(Util.getId(), Util.toUpperCaseFirstOne(vo.getName()), vo.getAlies(), vo.getIdtype(), vo.getDbname());
            for (ModelattributeOutVO attrVo : vo.getModelattributeOutVOS()) {
                model.addAttribute(new ModelAttribute(Util.getId(), attrVo.getName(), attrVo.getAlies(), attrVo.getType(), attrVo.getDbname(), attrVo.isColumnKey()));
            }
            models.add(model);
        }
        return models;
    }

    private String getBuildPath(String userId, String artifactId, String date) {
        return String.format(BUILD_PATH, userId, artifactId, date);
    }

    private String getZipPath(String userId, String artifactId, String date) {
        return String.format(ZIP_PATH, userId, artifactId, date);
    }
}

