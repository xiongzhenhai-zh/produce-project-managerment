package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IModelattributeBusiness {
    ResultBean add(HttpServletRequest request, ModelattributeEntity modelattributeEntity);

    ResultBean update(HttpServletRequest request, ModelattributeEntity modelattributeEntity);

    PageResultBean selectPageList(HttpServletRequest request, ModelattributeVO modelattributeVO);

    ResultBean delete(HttpServletRequest request, ModelattributeEntity modelattributeEntity);

    ResultBean queryById(HttpServletRequest request, Long id);

    ResultBean queryByIds(HttpServletRequest request, ModelattributeVO modelattributeVO);
}
