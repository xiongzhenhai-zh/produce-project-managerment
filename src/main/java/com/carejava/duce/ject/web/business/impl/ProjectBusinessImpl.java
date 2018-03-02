package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carejava.core.build.code.Build;
import com.carejava.core.build.code.OSType;
import com.carejava.core.build.model.Frame;
import com.carejava.core.build.utils.SystemUtil;
import com.carejava.duce.ject.web.business.IProjectBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.entity.ProjectEntity;
import com.carejava.duce.ject.web.enums.ProduceTypeEnums;
import com.carejava.duce.ject.web.in.vo.ProjectVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.outVO.ModelOutVO;
import com.carejava.duce.ject.web.service.IModelService;
import com.carejava.duce.ject.web.service.IProduceRecordService;
import com.carejava.duce.ject.web.service.IProjectService;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import com.carejava.duce.ject.web.utils.UtilPro;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * Projectbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("projectBusiness")
public class ProjectBusinessImpl extends BaseBusiness implements IProjectBusiness {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectBusinessImpl.class);

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IModelService modelService;

    @Autowired
    private IProduceRecordService produceRecordService;

    @Autowired
    private Build build;

    @Autowired
    private OSType osType;

    @Override
    public ResultBean add(HttpServletRequest request, ProjectEntity projectEntity) {

        if (!checkParam(projectEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        projectEntity.setCreateTime(Calendar.getInstance().getTime());
        projectEntity.setUserId(UserSessionUtil.getUserId(request));
        boolean result = projectService.add(projectEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(ProjectEntity projectEntity) {
        return !(projectEntity == null || StringUtils.isEmpty(projectEntity.getGroupid())
                /*|| !UtilPro.p.matcher(projectEntity.getArtifactid()).find()*/
                || StringUtils.isEmpty(projectEntity.getArtifactid()));
    }

    @Override
    public ResultBean update(HttpServletRequest request, ProjectEntity projectEntity) {

        if (!checkParam(projectEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        ResultBean resultBean = queryById(request, projectEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())) {
            return resultBean;
        }
        boolean success = projectService.update(projectEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(HttpServletRequest request, ProjectVO projectVO) {
        if (projectVO == null) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        projectVO.setAlies(UtilPro.getStringDBLike(projectVO.getAlies()));
        projectVO.setArtifactid(UtilPro.getStringDBLike(projectVO.getArtifactid()));
        projectVO.setName(UtilPro.getStringDBLike(projectVO.getName()));
        projectVO.setDatabasename(UtilPro.getStringDBLike(projectVO.getDatabasename()));
        projectVO.setAutor(UtilPro.getStringDBLike(projectVO.getAutor()));
        projectVO.setUserId(UserSessionUtil.getUserId(request));
        projectVO.setGroupid(UtilPro.getStringDBLike(projectVO.getGroupid()));
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = projectService.selectCount(projectVO);
        List<ProjectEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / projectVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            projectVO.setOffset((projectVO.getCurrentPage() - 1) * projectVO.getPageNum());

            list = projectService.selectPageList(projectVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(HttpServletRequest request, ProjectEntity projectEntity) {

        if (projectEntity.getId() == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        ResultBean resultBean = queryById(request, projectEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())) {
            return resultBean;
        }
        boolean success = projectService.delete(projectEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }

    }

    @Override
    public ResultBean queryById(HttpServletRequest request, Long id) {
        if (id == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        ProjectEntity projectEntity = projectService.queryById(id);
        if (projectEntity == null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        if (!projectEntity.getUserId().equals(UserSessionUtil.getUserId(request))) {
            return getResultBean(ErrorCode.NO_AUTHORITY_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS, projectEntity);
    }

    @Override
    public ResultBean produceById(HttpServletRequest request, ProjectEntity parames) {
        if (parames.getId() == null) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        ProjectEntity projectEntity = projectService.queryById(parames.getId());
        Long userId = UserSessionUtil.getUserId(request);
        if (projectEntity == null || !projectEntity.getUserId().equals(userId)) {
            return getResultBean(ErrorCode.NOT_EXISTS);
        }
        String zipPath = "";
        try {
            switch (ProduceTypeEnums.getEnumByType(projectEntity.getProduceType())) {
                case BYDB:
                    zipPath = build.buildByDB(projectEntity, String.valueOf(userId));
                    break;
                case BYMODEL:
                    String models = updateProjectModel(parames);
                    zipPath = build.buildByModel(projectEntity, projectService.queryModelsByProjectId(models), String.valueOf(userId));
                    break;
                default:

                    break;
            }

        } catch (Exception e) {
            LOGGER.error("异常={}", e);
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        ProduceRecordEntity produceRecordEntity = new ProduceRecordEntity();
        produceRecordEntity.setUserId(userId);
        produceRecordEntity.setFilePath(zipPath);
        produceRecordEntity.setCreateTime(new Date());
        produceRecordEntity.setSysType(SystemUtil.getOsName());
        produceRecordEntity.setProjectId(parames.getId());
        produceRecordService.add(produceRecordEntity);
        return getResultBean(ErrorCode.SUCCESS, produceRecordEntity.getId());
    }

    private String updateProjectModel(ProjectEntity parames) {
        JSONArray jsonArray = JSON.parseArray(parames.getModelIds());
        StringBuilder modelIds = new StringBuilder();
        for (Object aJsonArray : jsonArray) {
            JSONObject model = (JSONObject) aJsonArray;
            modelIds.append(model.getString("id")).append(",");
            JSONArray attrJsonArry = model.getJSONArray("attr");
            StringBuilder arrtIds = new StringBuilder();
            for (Object anAttrJsonArry : attrJsonArry) {
                JSONObject attr;
                attr = (JSONObject) anAttrJsonArry;
                arrtIds.append(attr.getString("id")).append(",");
            }
            ModelEntity modelEntity = new ModelEntity();
            modelEntity.setId(Long.parseLong(model.getString("id")));
            modelEntity.setAttributeIds(arrtIds.toString());
            if (arrtIds.toString().endsWith(",")) {
                modelEntity.setAttributeIds(arrtIds.toString().substring(0, arrtIds.length() - 1));
            }
            modelService.update(modelEntity);
        }
        parames.setModelIds(modelIds.toString());
        if (modelIds.toString().endsWith(",")) {
            parames.setModelIds(modelIds.toString().substring(0, modelIds.length()));
        }
        projectService.update(parames);
        return parames.getModelIds();
    }

    @Override
    public ResultBean queryModelsByProjectId(HttpServletRequest request, Long id) {
        ProjectEntity projectEntity = projectService.queryById(id);
        if (projectEntity == null) {
            return getResultBean(ErrorCode.NOT_EXISTS);
        }
        if (!projectEntity.getUserId().equals(UserSessionUtil.getUserId(request))) {
            return getResultBean(ErrorCode.NO_AUTHORITY_ERR);
        }
        List<ModelOutVO> list = projectService.queryModelsByProjectId(projectEntity.getModelIds());
        return getResultBean(ErrorCode.SUCCESS, (Serializable) list);
    }

    @Override
    public ResultBean queryOsType(HttpServletRequest request) {
        List<Frame> client = new ArrayList<>();
        if (null != osType.getClientTypes()) {
            for (String key : osType.getClientTypes().keySet()) {
                Frame frame = new Frame(osType.getClientTypes().get(key).getName(), osType.getClientTypes().get(key).getAlies());
                client.add(frame);
            }
        }
        List<Frame> service = new ArrayList<>();
        if (null != osType.getServiceTypes()) {
            for (String key : osType.getServiceTypes().keySet()) {
                Frame frame = new Frame(osType.getServiceTypes().get(key).getName(), osType.getServiceTypes().get(key).getAlies());
                service.add(frame);
            }
        }
        List<Frame> mobile = new ArrayList<>();
        if (null != osType.getMobileTypes()) {
            for (String key : osType.getMobileTypes().keySet()) {
                Frame frame = new Frame(osType.getMobileTypes().get(key).getName(), osType.getMobileTypes().get(key).getAlies());
                mobile.add(frame);
            }
        }
        Map<String, List<Frame>> map = new HashMap<>();
        map.put("client", client);
        map.put("service", service);
        map.put("mobile", mobile);
        return getResultBean(ErrorCode.SUCCESS, (Serializable) map);
    }
}
