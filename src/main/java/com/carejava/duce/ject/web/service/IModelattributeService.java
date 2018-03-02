package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;

import java.util.List;

public interface IModelattributeService {
    boolean add(ModelattributeEntity modelattributeEntity);

    boolean update(ModelattributeEntity modelattributeEntity);

    int selectCount(ModelattributeVO modelattributeVO);

    List<ModelattributeEntity> selectPageList(ModelattributeVO modelattributeVO);

    boolean delete(ModelattributeEntity modelattributeEntity);

    ModelattributeEntity queryById(Long id);
    
    List<ModelattributeEntity> queryAll();

    List<ModelattributeEntity> queryByIds(List<Long> ids);
}
