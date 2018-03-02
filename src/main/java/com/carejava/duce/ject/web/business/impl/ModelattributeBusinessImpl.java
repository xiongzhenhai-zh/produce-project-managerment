package com.carejava.duce.ject.web.business.impl;

import com.carejava.duce.ject.web.business.IModelattributeBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;
import com.carejava.duce.ject.web.service.IModelattributeService;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import com.carejava.duce.ject.web.utils.UtilPro;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * 项目生成管理平台<br>
 * Modelattributebusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("modelattributeBusiness")
public class ModelattributeBusinessImpl extends BaseBusiness implements IModelattributeBusiness{

    @Autowired
    private IModelattributeService modelattributeService;

    @Override
    public ResultBean add(HttpServletRequest request, ModelattributeEntity modelattributeEntity) {
        if (!checkParam(modelattributeEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        if (StringUtils.isEmpty(modelattributeEntity.getDbname())){
            modelattributeEntity.setDbname(modelattributeEntity.getName());
        }
        Date currentTime = Calendar.getInstance().getTime();
        modelattributeEntity.setUserId(UserSessionUtil.getUserId(request));
        modelattributeEntity.setCreateTime(currentTime);
        boolean result = modelattributeService.add(modelattributeEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(ModelattributeEntity modelattributeEntity) {

        if (modelattributeEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(HttpServletRequest request, ModelattributeEntity modelattributeEntity) {
        ResultBean resultBean =  queryById(request,modelattributeEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())){
            return resultBean;
        }
        boolean success = modelattributeService.update(modelattributeEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(HttpServletRequest request, ModelattributeVO modelattributeVO) {

        if (modelattributeVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        modelattributeVO.setName(UtilPro.getStringDBLike(modelattributeVO.getName()));
        modelattributeVO.setDbname(UtilPro.getStringDBLike(modelattributeVO.getDbname()));
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);
        modelattributeVO.setUserId(UserSessionUtil.getUserId(request));
        int totalNum = modelattributeService.selectCount(modelattributeVO);
        List<ModelattributeEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / modelattributeVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            modelattributeVO.setOffset((modelattributeVO.getCurrentPage() - 1) * modelattributeVO.getPageNum());

            list = modelattributeService.selectPageList(modelattributeVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(HttpServletRequest request, ModelattributeEntity modelattributeEntity) {
        ResultBean resultBean = queryById(request, modelattributeEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())) {
            return resultBean;
        }
        boolean success = modelattributeService.delete(modelattributeEntity);
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

        ModelattributeEntity modelattributeEntity = modelattributeService.queryById(id);
        if (modelattributeEntity == null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        if (!modelattributeEntity.getUserId().equals(UserSessionUtil.getUserId(request))) {
            return getResultBean(ErrorCode.NO_AUTHORITY_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS, (Serializable) modelattributeEntity);
    }

    @Override
    public ResultBean queryByIds(HttpServletRequest request, ModelattributeVO modelattributeVO) {
        List<Long> ids = modelattributeVO.getIds();
        if (ids == null || ids.size() == 0) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);
        List<ModelattributeEntity> list = modelattributeService.queryByIds(ids);
        result.setTotalNum(ids.size());
        int totalPage = (ids.size() - 1) / modelattributeVO.getPageNum() + 1;
        result.setTotalPage(totalPage);
        modelattributeVO.setOffset((modelattributeVO.getCurrentPage() - 1) * modelattributeVO.getPageNum());
        int toIndex = modelattributeVO.getOffset() + modelattributeVO.getPageNum();
        toIndex =toIndex > ids.size() ?  ids.size() - 1:toIndex;
        List<ModelattributeEntity> listReturn = new ArrayList<>();
        try {
            listReturn.addAll(list.subList(modelattributeVO.getOffset(), toIndex));
        } catch (Exception e) {
            LOGGER.error("e=", e);
        }
        result.setContent((Serializable) listReturn);
        return result;
    }

}
