package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.IModelattributeBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * Modelattribute控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/modelattribute", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class ModelattributeController extends BaseController {

    @Autowired
    private IModelattributeBusiness modelattributeBusiness;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody ModelattributeEntity modelattributeEntity) {
        return modelattributeBusiness.add(request, modelattributeEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody ModelattributeEntity modelattributeEntity) {
        return modelattributeBusiness.update(request, modelattributeEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody ModelattributeVO modelattributeVO) {
        return modelattributeBusiness.selectPageList(request, modelattributeVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody ModelattributeEntity modelattributeEntity) {
        return modelattributeBusiness.delete(request, modelattributeEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody ModelattributeEntity modelattributeEntity) {
        return modelattributeBusiness.queryById(request, modelattributeEntity.getId());
    }

    @RequestMapping(value = "queryByIds")
    @ResponseBody
    public ResultBean queryByIds(HttpServletRequest request, @RequestBody ModelattributeVO modelattributeVO) {
        return modelattributeBusiness.queryByIds(request, modelattributeVO);
    }

}
