package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.carejava.duce.ject.web.business.ICmRoleBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.ICmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 项目生成管理平台<br>
 * CmRolebusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("cmRoleBusiness")
public class CmRoleBusinessImpl extends BaseBusiness implements ICmRoleBusiness{

    @Autowired
    private ICmRoleService cmRoleService;

    @Override
    public ResultBean add(CmRoleEntity cmRoleEntity) {

        if (!checkParam(cmRoleEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        Date currentTime = Calendar.getInstance().getTime();
        boolean result = cmRoleService.add(cmRoleEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(CmRoleEntity cmRoleEntity) {

        if (cmRoleEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(CmRoleEntity cmRoleEntity) {

        if (cmRoleEntity == null ){
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmRoleService.update(cmRoleEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(CmRoleVO cmRoleVO) {

        if (cmRoleVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = cmRoleService.selectCount(cmRoleVO);
        List<CmRoleEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / cmRoleVO.getPageNum() + 1;
            result.setTotalPage(totalPage);

            cmRoleVO.setOffset((cmRoleVO.getCurrentPage() - 1) * cmRoleVO.getPageNum());

            list = cmRoleService.selectPageList(cmRoleVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(CmRoleEntity cmRoleEntity) {

        if (cmRoleEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmRoleService.delete(cmRoleEntity);
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
        CmRoleEntity cmRoleEntity = cmRoleService.queryById(id);
        if (cmRoleEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS,(Serializable) cmRoleEntity);
    }

}
