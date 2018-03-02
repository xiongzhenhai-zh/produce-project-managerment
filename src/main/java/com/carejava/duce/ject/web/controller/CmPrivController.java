package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.ICmPrivBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 项目生成管理平台<br>
 * CmPriv控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/cmPriv", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
public class CmPrivController extends BaseController{

    @Autowired
    private ICmPrivBusiness cmPrivBusiness;

    @RequestMapping(value= "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody CmPrivEntity cmPrivEntity) {
        return cmPrivBusiness.add(cmPrivEntity);
    }

    @RequestMapping(value= "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody CmPrivEntity cmPrivEntity) {
        return cmPrivBusiness.update(cmPrivEntity);
    }

    @RequestMapping(value= "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody CmPrivVO cmPrivVO) {
        return cmPrivBusiness.selectPageList(cmPrivVO);
    }

    @RequestMapping(value= "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody CmPrivEntity cmPrivEntity) {
        return cmPrivBusiness.delete(cmPrivEntity);
    }

    @RequestMapping(value= "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody CmPrivEntity cmPrivEntity) {
        return cmPrivBusiness.queryById(cmPrivEntity.getId());
    }

    @RequestMapping(value= "queryUserAuthority")
    @ResponseBody
    public ResultBean queryUserAuthority(HttpServletRequest request, @RequestBody CmPrivEntity cmPrivEntity) {
        return cmPrivBusiness.queryUserAuthority(request);
    }

}
