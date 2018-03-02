package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.carejava.duce.ject.web.business.IModelBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.IModelService;
import com.carejava.duce.ject.web.utils.UserSessionUtil;
import com.carejava.duce.ject.web.utils.UtilPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 项目生成管理平台<br>
 * Modelbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("modelBusiness")
public class ModelBusinessImpl extends BaseBusiness implements IModelBusiness{

    @Autowired
    private IModelService modelService;

    @Override
    public ResultBean add(HttpServletRequest request, ModelEntity modelEntity) {

        if (!checkParam(modelEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        Date currentTime = Calendar.getInstance().getTime();
        modelEntity.setIdtype("auto_increment");
        modelEntity.setUserId(UserSessionUtil.getUserId(request));
        modelEntity.setCreateTime(currentTime);
        boolean result = modelService.add(modelEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(ModelEntity modelEntity) {
        return modelEntity != null;
    }

    @Override
    public ResultBean update(HttpServletRequest request, ModelEntity modelEntity) {

        if (modelEntity == null ){
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        ResultBean resultBean = queryById(request, modelEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())) {
            return resultBean;
        }
        boolean success = modelService.update(modelEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(HttpServletRequest request, ModelVO modelVO) {

        if (modelVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        modelVO.setName(UtilPro.getStringDBLike(modelVO.getName()));
        modelVO.setDbname(UtilPro.getStringDBLike(modelVO.getDbname()));
        modelVO.setAlies(UtilPro.getStringDBLike(modelVO.getAlies()));
        modelVO.setUserId(UserSessionUtil.getUserId(request));
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = modelService.selectCount(modelVO);
        List<ModelEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / modelVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            modelVO.setOffset((modelVO.getCurrentPage() - 1) * modelVO.getPageNum());

            list = modelService.selectPageList(modelVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(HttpServletRequest request, ModelEntity modelEntity) {
        if (modelEntity == null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        ResultBean resultBean = queryById(request, modelEntity.getId());
        if (!resultBean.getCode().equals(ErrorCode.SUCCESS.getErrorCode())) {
            return resultBean;
        }
        boolean success = modelService.delete(modelEntity);
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
        ModelEntity modelEntity = modelService.queryById(id);
        if (modelEntity == null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        if (!modelEntity.getUserId().equals(UserSessionUtil.getUserId(request))) {
            return getResultBean(ErrorCode.NO_AUTHORITY_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS, modelEntity);
    }

}
