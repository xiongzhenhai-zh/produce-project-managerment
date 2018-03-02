package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;

import java.util.List;

public interface ICmPrivService {
    boolean add(CmPrivEntity cmPrivEntity);

    boolean update(CmPrivEntity cmPrivEntity);

    int selectCount(CmPrivVO cmPrivVO);

    List<CmPrivEntity> selectPageList(CmPrivVO cmPrivVO);

    boolean delete(CmPrivEntity cmPrivEntity);

    CmPrivEntity queryById(Long id);
    
    List<CmPrivEntity> queryAll();
}
