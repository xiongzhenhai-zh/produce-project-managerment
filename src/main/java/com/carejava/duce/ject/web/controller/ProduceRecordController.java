package com.carejava.duce.ject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;
import com.carejava.duce.ject.web.core.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.carejava.duce.ject.web.business.IProduceRecordBusiness;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 项目生成管理平台<br>
 * ProduceRecord控制层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2018年01月31日]
 */
@Controller
@RequestMapping(value = "/produceRecord", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
public class ProduceRecordController extends BaseController{

    private final static Logger LOGGER = LoggerFactory.getLogger(ProduceRecordController.class);

    @Autowired
    private IProduceRecordBusiness produceRecordBusiness;

    /*@RequestMapping(value = "add")
    @ResponseBody
    public ResultBean add(HttpServletRequest request, @RequestBody ProduceRecordEntity produceRecordEntity) {
        return produceRecordBusiness.add(produceRecordEntity);
    }

    @RequestMapping(value= "update")
    @ResponseBody
    public ResultBean update(HttpServletRequest request, @RequestBody ProduceRecordEntity produceRecordEntity) {
        return produceRecordBusiness.update(produceRecordEntity);
    }*/

    @RequestMapping(value= "selectPageList")
    @ResponseBody
    public ResultBean selectPageList(HttpServletRequest request, @RequestBody ProduceRecordVO produceRecordVO) {
        return produceRecordBusiness.selectPageList(request, produceRecordVO);
    }

    @RequestMapping(value= "delete")
    @ResponseBody
    public ResultBean delete(HttpServletRequest request, @RequestBody ProduceRecordEntity produceRecordEntity) {
        return produceRecordBusiness.delete(request, produceRecordEntity);
    }

    /*@RequestMapping(value= "queryById", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean queryById(HttpServletRequest request, @RequestBody ProduceRecordEntity produceRecordEntity) {
        return produceRecordBusiness.queryById(produceRecordEntity.getId());
    }*/
}
