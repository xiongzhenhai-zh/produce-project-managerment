package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.ICmRoleBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * CmRole控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/cmRole", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class CmRoleController extends BaseController {

    @Autowired
    private ICmRoleBusiness cmRoleBusiness;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody CmRoleEntity cmRoleEntity) {
        return cmRoleBusiness.add(cmRoleEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody CmRoleEntity cmRoleEntity) {
        return cmRoleBusiness.update(cmRoleEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody CmRoleVO cmRoleVO) {
        return cmRoleBusiness.selectPageList(cmRoleVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody CmRoleEntity cmRoleEntity) {
        return cmRoleBusiness.delete(cmRoleEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody CmRoleEntity cmRoleEntity) {
        return cmRoleBusiness.queryById(cmRoleEntity.getId());
    }

}
