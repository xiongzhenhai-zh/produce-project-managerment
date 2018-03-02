package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.ModelattributeMapper;
import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;
import com.carejava.duce.ject.web.service.IModelattributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Modelattribute Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("modelattributeService")
public class ModelattributeServiceImpl implements IModelattributeService{

    @Autowired
    private ModelattributeMapper modelattributeMapper;
    
    @Override
    public boolean add(ModelattributeEntity modelattributeEntity) {
        int num = modelattributeMapper.insert(modelattributeEntity);
        return num == 1;
    }

    @Override
    public boolean update(ModelattributeEntity modelattributeEntity) {
        int num = modelattributeMapper.update(modelattributeEntity);
        return num == 1;
    }

    @Override
    public int selectCount(ModelattributeVO modelattributeVO) {
        return modelattributeMapper.selectCount(modelattributeVO);
    }

    @Override
    public List<ModelattributeEntity> selectPageList(ModelattributeVO modelattributeVO) {
        return modelattributeMapper.selectPageList(modelattributeVO);
    }

    @Override
    public boolean delete(ModelattributeEntity modelattributeEntity) {
        int num = modelattributeMapper.delete(modelattributeEntity);
        return num == 1;
    }

    @Override
    public ModelattributeEntity queryById(Long id) {
        return modelattributeMapper.queryById(id);
    }

    @Override
    public List<ModelattributeEntity> queryAll() {
        return modelattributeMapper.queryAll();
    }

    @Override
    public List<ModelattributeEntity> queryByIds(List<Long> ids) {
        return modelattributeMapper.queryByIds(ids);
    }

}
