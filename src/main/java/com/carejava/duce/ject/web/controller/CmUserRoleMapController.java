package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.ICmUserRoleMapBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * CmUserRoleMap控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/cmUserRoleMap", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class CmUserRoleMapController extends BaseController {

    @Autowired
    private ICmUserRoleMapBusiness cmUserRoleMapBusiness;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody CmUserRoleMapEntity cmUserRoleMapEntity) {
        return cmUserRoleMapBusiness.add(cmUserRoleMapEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody CmUserRoleMapEntity cmUserRoleMapEntity) {
        return cmUserRoleMapBusiness.update(cmUserRoleMapEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody CmUserRoleMapVO cmUserRoleMapVO) {
        return cmUserRoleMapBusiness.selectPageList(cmUserRoleMapVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody CmUserRoleMapEntity cmUserRoleMapEntity) {
        return cmUserRoleMapBusiness.delete(cmUserRoleMapEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody CmUserRoleMapEntity cmUserRoleMapEntity) {
        return cmUserRoleMapBusiness.queryById(cmUserRoleMapEntity.getId());
    }

}
