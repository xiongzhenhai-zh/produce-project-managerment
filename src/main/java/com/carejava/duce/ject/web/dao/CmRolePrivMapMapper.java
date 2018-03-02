package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface CmRolePrivMapMapper {
    
    int insert(CmRolePrivMapEntity cmRolePrivMapEntity);
    
    int update(CmRolePrivMapEntity cmRolePrivMapEntity);
    
    int selectCount(CmRolePrivMapVO cmRolePrivMapVO);
    
    List<CmRolePrivMapEntity> selectPageList(CmRolePrivMapVO cmRolePrivMapVO);
    
    int delete(CmRolePrivMapEntity cmRolePrivMapEntity);

    CmRolePrivMapEntity queryById(Long id);
    
    List<CmRolePrivMapEntity> queryAll();

}
