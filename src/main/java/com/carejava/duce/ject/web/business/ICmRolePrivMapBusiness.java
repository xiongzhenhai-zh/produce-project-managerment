package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;

public interface ICmRolePrivMapBusiness {
    ResultBean add(CmRolePrivMapEntity cmRolePrivMapEntity);

    ResultBean update(CmRolePrivMapEntity cmRolePrivMapEntity);

    PageResultBean selectPageList(CmRolePrivMapVO cmRolePrivMapVO);

    ResultBean delete(CmRolePrivMapEntity cmRolePrivMapEntity);

    ResultBean queryById(Long id);

}
