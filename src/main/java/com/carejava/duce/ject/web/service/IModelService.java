package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;

import java.util.List;

public interface IModelService {
    boolean add(ModelEntity modelEntity);

    boolean update(ModelEntity modelEntity);

    int selectCount(ModelVO modelVO);

    List<ModelEntity> selectPageList(ModelVO modelVO);

    boolean delete(ModelEntity modelEntity);

    ModelEntity queryById(Long id);
    
    List<ModelEntity> queryAll();
}
