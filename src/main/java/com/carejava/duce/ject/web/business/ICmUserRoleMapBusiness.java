package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;

public interface ICmUserRoleMapBusiness {
    ResultBean add(CmUserRoleMapEntity cmUserRoleMapEntity);

    ResultBean update(CmUserRoleMapEntity cmUserRoleMapEntity);

    PageResultBean selectPageList(CmUserRoleMapVO cmUserRoleMapVO);

    ResultBean delete(CmUserRoleMapEntity cmUserRoleMapEntity);

    ResultBean queryById(Long id);

}
