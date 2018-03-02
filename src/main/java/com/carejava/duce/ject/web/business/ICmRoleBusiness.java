package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.PageResultBean;
import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;

public interface ICmRoleBusiness {
    ResultBean add(CmRoleEntity cmRoleEntity);

    ResultBean update(CmRoleEntity cmRoleEntity);

    PageResultBean selectPageList(CmRoleVO cmRoleVO);

    ResultBean delete(CmRoleEntity cmRoleEntity);

    ResultBean queryById(Long id);

}
