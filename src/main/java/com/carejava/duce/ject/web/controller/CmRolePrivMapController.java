package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carejava.duce.ject.web.business.ICmRolePrivMapBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目生成管理平台<br>
 * CmRolePrivMap控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Controller
@RequestMapping(value = "/cmRolePrivMap", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
public class CmRolePrivMapController extends BaseController {

    @Autowired
    private ICmRolePrivMapBusiness cmRolePrivMapBusiness;

    @RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody CmRolePrivMapEntity cmRolePrivMapEntity) {
        return cmRolePrivMapBusiness.add(cmRolePrivMapEntity);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody CmRolePrivMapEntity cmRolePrivMapEntity) {
        return cmRolePrivMapBusiness.update(cmRolePrivMapEntity);
    }

    @RequestMapping(value = "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody CmRolePrivMapVO cmRolePrivMapVO) {
        return cmRolePrivMapBusiness.selectPageList(cmRolePrivMapVO);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody CmRolePrivMapEntity cmRolePrivMapEntity) {
        return cmRolePrivMapBusiness.delete(cmRolePrivMapEntity);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody CmRolePrivMapEntity cmRolePrivMapEntity) {
        return cmRolePrivMapBusiness.queryById(cmRolePrivMapEntity.getId());
    }

}
