package com.carejava.duce.ject.web.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.carejava.duce.ject.web.business.ICmPrivBusiness;
import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;
import com.carejava.duce.ject.web.enums.ErrorCode;
import com.carejava.duce.ject.web.service.ICmPrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 项目生成管理平台<br>
 * CmPrivbusiness 层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Component("cmPrivBusiness")
public class CmPrivBusinessImpl extends BaseBusiness implements ICmPrivBusiness{

    @Autowired
    private ICmPrivService cmPrivService;

    @Override
    public ResultBean add(CmPrivEntity cmPrivEntity) {

        if (!checkParam(cmPrivEntity)) {
            return getResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        boolean result = cmPrivService.add(cmPrivEntity);
        if (result) {
            return getResultBean(ErrorCode.SUCCESS);
        }else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    private boolean checkParam(CmPrivEntity cmPrivEntity) {

        if (cmPrivEntity== null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ResultBean update(CmPrivEntity cmPrivEntity) {

        if (cmPrivEntity == null ){
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmPrivService.update(cmPrivEntity);
        if (success) {
            return getResultBean(ErrorCode.SUCCESS);
        } else {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
    }

    @Override
    public PageResultBean selectPageList(CmPrivVO cmPrivVO) {

        if (cmPrivVO == null ) {
            return new PageResultBean(ErrorCode.ERR_INVALID_PARAMS);
        }
        PageResultBean result = new PageResultBean(ErrorCode.SUCCESS);

        int totalNum = cmPrivService.selectCount(cmPrivVO);
        List<CmPrivEntity> list = new ArrayList<>();
        if (totalNum > 0) {
            result.setTotalNum(totalNum);
            int totalPage = (totalNum - 1) / cmPrivVO.getPageNum() + 1;
            result.setTotalPage(totalPage);
            cmPrivVO.setOffset((cmPrivVO.getCurrentPage() - 1) * cmPrivVO.getPageNum());

            list = cmPrivService.selectPageList(cmPrivVO);
        }
        result.setContent((Serializable) list);
        return result;
    }

    @Override
    public ResultBean delete(CmPrivEntity cmPrivEntity) {

        if (cmPrivEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        boolean success = cmPrivService.delete(cmPrivEntity);
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
        CmPrivEntity cmPrivEntity = cmPrivService.queryById(id);
        if (cmPrivEntity==null) {
            return getResultBean(ErrorCode.ERR_SYSTEM_ERR);
        }
        return getResultBean(ErrorCode.SUCCESS,(Serializable) cmPrivEntity);
    }

    @Override
    public ResultBean queryUserAuthority(HttpServletRequest request) {
        CmPrivVO cmPrivVO = new CmPrivVO();
        cmPrivVO.setOffset(0);
        cmPrivVO.setPageNum(100);
        List<CmPrivEntity> list = cmPrivService.selectPageList(cmPrivVO);
        List<CmPrivEntity> privsTree = buildToTree(list);
        return getResultBean(ErrorCode.SUCCESS, (Serializable) privsTree);
    }

    private List<CmPrivEntity> buildToTree(List<CmPrivEntity> privs) {
        List<CmPrivEntity> privsTree = new ArrayList<CmPrivEntity>();
        for (CmPrivEntity priv : privs) {
            if(priv.getPid()!=null){
                //获得父节点，并加入的父节点
                CmPrivEntity parent = getParentNode(priv.getPid(), privs);
                if(parent != null){
                    List<CmPrivEntity> subPrivs = parent.getSubPrivs();
                    if(subPrivs == null){
                        subPrivs = new ArrayList<CmPrivEntity>();
                    }
                    subPrivs.add(priv);
                    parent.setSubPrivs(subPrivs);
                }
            }else{
                privsTree.add(priv);
            }
        }
        return privsTree;
    }

    private CmPrivEntity getParentNode(Long pid, List<CmPrivEntity> nodes) {
        for(CmPrivEntity node: nodes){
            if(pid.equals(node.getId())){
                return node;
            }
        }
        return null;
    }

}
