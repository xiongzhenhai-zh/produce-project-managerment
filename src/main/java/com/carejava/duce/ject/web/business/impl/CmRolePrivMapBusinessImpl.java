package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.carejava.duce.ject.web.business.ICmRolePrivMapBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.ICmRolePrivMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 项目生成管理平台<br>
 * CmRolePrivMapbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("cmRolePrivMapBusiness")
public class CmRolePrivMapBusinessImpl extends BaseBusiness implements ICmRolePrivMapBusiness{

    @Autowired
    private ICmRolePrivMapService cmRolePrivMapService;

    @Override
    public ResultBean add(CmRolePrivMapEntity cmRolePrivMapEntity) {

        if (!checkParam(cmRolePrivMapEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        Date currentTime = Calendar.getInstance().getTime();
        boolean result = cmRolePrivMapService.add(cmRolePrivMapEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(CmRolePrivMapEntity cmRolePrivMapEntity) {

        if (cmRolePrivMapEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(CmRolePrivMapEntity cmRolePrivMapEntity) {

        if (cmRolePrivMapEntity == null ){
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmRolePrivMapService.update(cmRolePrivMapEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(CmRolePrivMapVO cmRolePrivMapVO) {

        if (cmRolePrivMapVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = cmRolePrivMapService.selectCount(cmRolePrivMapVO);
        List<CmRolePrivMapEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / cmRolePrivMapVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            cmRolePrivMapVO.setOffset((cmRolePrivMapVO.getCurrentPage() - 1) * cmRolePrivMapVO.getPageNum());

            list = cmRolePrivMapService.selectPageList(cmRolePrivMapVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(CmRolePrivMapEntity cmRolePrivMapEntity) {

        if (cmRolePrivMapEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmRolePrivMapService.delete(cmRolePrivMapEntity);
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
        CmRolePrivMapEntity cmRolePrivMapEntity = cmRolePrivMapService.queryById(id);
        if (cmRolePrivMapEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS,(Serializable) cmRolePrivMapEntity);
    }

}
