package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public interface CmUserRoleMapMapper {
    
    int insert(CmUserRoleMapEntity cmUserRoleMapEntity);
    
    int update(CmUserRoleMapEntity cmUserRoleMapEntity);
    
    int selectCount(CmUserRoleMapVO cmUserRoleMapVO);
    
    List<CmUserRoleMapEntity> selectPageList(CmUserRoleMapVO cmUserRoleMapVO);
    
    int delete(CmUserRoleMapEntity cmUserRoleMapEntity);

    CmUserRoleMapEntity queryById(Long id);
    
    List<CmUserRoleMapEntity> queryAll();

}
