package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.IModelBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * Model控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/model", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class ModelController extends BaseController {

    @Autowired
    private IModelBusiness modelBusiness;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody ModelEntity modelEntity) {
        return modelBusiness.add(request, modelEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody ModelEntity modelEntity) {
        return modelBusiness.update(request, modelEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody ModelVO modelVO) {
        return modelBusiness.selectPageList(request, modelVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody ModelEntity modelEntity) {
        return modelBusiness.delete(request, modelEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody ModelEntity modelEntity) {
        return modelBusiness.queryById(request, modelEntity.getId());
    }

}
