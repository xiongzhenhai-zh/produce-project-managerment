package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;

import java.util.List;

public interface ICmRoleService {
    boolean add(CmRoleEntity cmRoleEntity);

    boolean update(CmRoleEntity cmRoleEntity);

    int selectCount(CmRoleVO cmRoleVO);

    List<CmRoleEntity> selectPageList(CmRoleVO cmRoleVO);

    boolean delete(CmRoleEntity cmRoleEntity);

    CmRoleEntity queryById(Long id);
    
    List<CmRoleEntity> queryAll();

    List<CmRoleEntity> queryByUserId(Long id);
}
