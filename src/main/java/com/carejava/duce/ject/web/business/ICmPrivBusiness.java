package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;

import javax.servlet.http.HttpServletRequest;

public interface ICmPrivBusiness {
    ResultBean add(CmPrivEntity cmPrivEntity);

    ResultBean update(CmPrivEntity cmPrivEntity);

    PageResultBean selectPageList(CmPrivVO cmPrivVO);

    ResultBean delete(CmPrivEntity cmPrivEntity);

    ResultBean queryById(Long id);

    ResultBean queryUserAuthority(HttpServletRequest request);
}
