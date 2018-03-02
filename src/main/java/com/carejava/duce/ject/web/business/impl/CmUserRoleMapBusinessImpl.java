package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.carejava.duce.ject.web.business.ICmUserRoleMapBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.ICmUserRoleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 项目生成管理平台<br>
 * CmUserRoleMapbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("cmUserRoleMapBusiness")
public class CmUserRoleMapBusinessImpl extends BaseBusiness implements ICmUserRoleMapBusiness{

    @Autowired
    private ICmUserRoleMapService cmUserRoleMapService;

    @Override
    public ResultBean add(CmUserRoleMapEntity cmUserRoleMapEntity) {

        if (!checkParam(cmUserRoleMapEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        Date currentTime = Calendar.getInstance().getTime();
        boolean result = cmUserRoleMapService.add(cmUserRoleMapEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(CmUserRoleMapEntity cmUserRoleMapEntity) {

        if (cmUserRoleMapEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(CmUserRoleMapEntity cmUserRoleMapEntity) {

        if (cmUserRoleMapEntity == null ){
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmUserRoleMapService.update(cmUserRoleMapEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(CmUserRoleMapVO cmUserRoleMapVO) {

        if (cmUserRoleMapVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = cmUserRoleMapService.selectCount(cmUserRoleMapVO);
        List<CmUserRoleMapEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / cmUserRoleMapVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            cmUserRoleMapVO.setOffset((cmUserRoleMapVO.getCurrentPage() - 1) * cmUserRoleMapVO.getPageNum());

            list = cmUserRoleMapService.selectPageList(cmUserRoleMapVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(CmUserRoleMapEntity cmUserRoleMapEntity) {

        if (cmUserRoleMapEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmUserRoleMapService.delete(cmUserRoleMapEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }

    }

    @Override
    public ResultBean queryById(Long id) {

        if (id==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        CmUserRoleMapEntity cmUserRoleMapEntity = cmUserRoleMapService.queryById(id);
        if (cmUserRoleMapEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS,(Serializable) cmUserRoleMapEntity);
    }

}
