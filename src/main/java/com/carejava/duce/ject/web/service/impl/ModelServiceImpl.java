package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.ModelMapper;
import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;
import com.carejava.duce.ject.web.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Model Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("modelService")
public class ModelServiceImpl implements IModelService{

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public boolean add(ModelEntity modelEntity) {
        int num = modelMapper.insert(modelEntity);
        return num == 1;
    }

    @Override
    public boolean update(ModelEntity modelEntity) {
        int num = modelMapper.update(modelEntity);
        return num == 1;
    }

    @Override
    public int selectCount(ModelVO modelVO) {
        return modelMapper.selectCount(modelVO);
    }

    @Override
    public List<ModelEntity> selectPageList(ModelVO modelVO) {
        return modelMapper.selectPageList(modelVO);
    }

    @Override
    public boolean delete(ModelEntity modelEntity) {
        int num = modelMapper.delete(modelEntity);
        return num == 1;
    }

    @Override
    public ModelEntity queryById(Long id) {
        return modelMapper.queryById(id);
    }

    @Override
    public List<ModelEntity> queryAll() {
        return modelMapper.queryAll();
    }

}
