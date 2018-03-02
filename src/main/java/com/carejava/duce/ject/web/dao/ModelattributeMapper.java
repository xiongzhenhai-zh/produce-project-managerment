package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.ModelattributeEntity;
import com.carejava.duce.ject.web.in.vo.ModelattributeVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface ModelattributeMapper {
    
    int insert(ModelattributeEntity modelattributeEntity);
    
    int update(ModelattributeEntity modelattributeEntity);
    
    int selectCount(ModelattributeVO modelattributeVO);
    
    List<ModelattributeEntity> selectPageList(ModelattributeVO modelattributeVO);
    
    int delete(ModelattributeEntity modelattributeEntity);

    ModelattributeEntity queryById(Long id);
    
    List<ModelattributeEntity> queryAll();

    List<ModelattributeEntity> queryByIds(List<Long> ids);
}
