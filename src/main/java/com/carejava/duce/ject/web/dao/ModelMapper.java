package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.ModelEntity;
import com.carejava.duce.ject.web.in.vo.ModelVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface ModelMapper {
    
    int insert(ModelEntity modelEntity);
    
    int update(ModelEntity modelEntity);
    
    int selectCount(ModelVO modelVO);
    
    List<ModelEntity> selectPageList(ModelVO modelVO);
    
    int delete(ModelEntity modelEntity);

    ModelEntity queryById(Long id);
    
    List<ModelEntity> queryAll();

}
