package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.CmUserRoleMapMapper;
import com.carejava.duce.ject.web.entity.CmUserRoleMapEntity;
import com.carejava.duce.ject.web.in.vo.CmUserRoleMapVO;
import com.carejava.duce.ject.web.service.ICmUserRoleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * CmUserRoleMap Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("cmUserRoleMapService")
public class CmUserRoleMapServiceImpl implements ICmUserRoleMapService{

    @Autowired
    private CmUserRoleMapMapper cmUserRoleMapMapper;
    
    @Override
    public boolean add(CmUserRoleMapEntity cmUserRoleMapEntity) {
        int num = cmUserRoleMapMapper.insert(cmUserRoleMapEntity);
        return num == 1;
    }

    @Override
    public boolean update(CmUserRoleMapEntity cmUserRoleMapEntity) {
        int num = cmUserRoleMapMapper.update(cmUserRoleMapEntity);
        return num == 1;
    }

    @Override
    public int selectCount(CmUserRoleMapVO cmUserRoleMapVO) {
        return cmUserRoleMapMapper.selectCount(cmUserRoleMapVO);
    }

    @Override
    public List<CmUserRoleMapEntity> selectPageList(CmUserRoleMapVO cmUserRoleMapVO) {
        return cmUserRoleMapMapper.selectPageList(cmUserRoleMapVO);
    }

    @Override
    public boolean delete(CmUserRoleMapEntity cmUserRoleMapEntity) {
        int num = cmUserRoleMapMapper.delete(cmUserRoleMapEntity);
        return num == 1;
    }

    @Override
    public CmUserRoleMapEntity queryById(Long id) {
        return cmUserRoleMapMapper.queryById(id);
    }

    @Override
    public List<CmUserRoleMapEntity> queryAll() {
        return cmUserRoleMapMapper.queryAll();
    }

}
