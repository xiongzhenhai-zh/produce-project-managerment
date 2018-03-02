package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;

import javax.servlet.http.HttpServletRequest;

public interface IModelBusiness {
    ResultBean add(HttpServletRequest request, ModelEntity modelEntity);

    ResultBean update(HttpServletRequest request, ModelEntity modelEntity);

    PageResultBean selectPageList(HttpServletRequest request, ModelVO modelVO);

    ResultBean delete(HttpServletRequest request, ModelEntity modelEntity);

    ResultBean queryById(HttpServletRequest request, Long id);

}
