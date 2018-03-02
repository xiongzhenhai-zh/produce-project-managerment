package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface CmPrivMapper {
    
    int insert(CmPrivEntity cmPrivEntity);
    
    int update(CmPrivEntity cmPrivEntity);
    
    int selectCount(CmPrivVO cmPrivVO);
    
    List<CmPrivEntity> selectPageList(CmPrivVO cmPrivVO);
    
    int delete(CmPrivEntity cmPrivEntity);

    CmPrivEntity queryById(Long id);
    
    List<CmPrivEntity> queryAll();

    List<CmPrivEntity> getAllAPI(Long id);

    List<CmPrivEntity> getAllMenu(Long id);
}
