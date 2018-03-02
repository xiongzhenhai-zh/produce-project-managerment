package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface CmRoleMapper {
    
    int insert(CmRoleEntity cmRoleEntity);
    
    int update(CmRoleEntity cmRoleEntity);
    
    int selectCount(CmRoleVO cmRoleVO);
    
    List<CmRoleEntity> selectPageList(CmRoleVO cmRoleVO);
    
    int delete(CmRoleEntity cmRoleEntity);

    CmRoleEntity queryById(Long id);
    
    List<CmRoleEntity> queryAll();

    List<CmRoleEntity> queryByUserId(Long userId);
}
