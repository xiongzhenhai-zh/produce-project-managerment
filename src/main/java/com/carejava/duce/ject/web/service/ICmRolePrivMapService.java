package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;

import java.util.List;

public interface ICmRolePrivMapService {
    boolean add(CmRolePrivMapEntity cmRolePrivMapEntity);

    boolean update(CmRolePrivMapEntity cmRolePrivMapEntity);

    int selectCount(CmRolePrivMapVO cmRolePrivMapVO);

    List<CmRolePrivMapEntity> selectPageList(CmRolePrivMapVO cmRolePrivMapVO);

    boolean delete(CmRolePrivMapEntity cmRolePrivMapEntity);

    CmRolePrivMapEntity queryById(Long id);
    
    List<CmRolePrivMapEntity> queryAll();
}
