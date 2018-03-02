package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;

import java.util.List;

public interface ICmUserRoleMapService {
    boolean add(CmUserRoleMapEntity cmUserRoleMapEntity);

    boolean update(CmUserRoleMapEntity cmUserRoleMapEntity);

    int selectCount(CmUserRoleMapVO cmUserRoleMapVO);

    List<CmUserRoleMapEntity> selectPageList(CmUserRoleMapVO cmUserRoleMapVO);

    boolean delete(CmUserRoleMapEntity cmUserRoleMapEntity);

    CmUserRoleMapEntity queryById(Long id);
    
    List<CmUserRoleMapEntity> queryAll();
}
