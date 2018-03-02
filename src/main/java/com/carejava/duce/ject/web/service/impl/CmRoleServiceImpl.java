package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.CmRoleMapper;
import com.carejava.duce.ject.web.entity.CmRoleEntity;
import com.carejava.duce.ject.web.in.vo.CmRoleVO;
import com.carejava.duce.ject.web.service.ICmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * CmRole Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("cmRoleService")
public class CmRoleServiceImpl implements ICmRoleService{

    @Autowired
    private CmRoleMapper cmRoleMapper;
    
    @Override
    public boolean add(CmRoleEntity cmRoleEntity) {
        int num = cmRoleMapper.insert(cmRoleEntity);
        return num == 1;
    }

    @Override
    public boolean update(CmRoleEntity cmRoleEntity) {
        int num = cmRoleMapper.update(cmRoleEntity);
        return num == 1;
    }

    @Override
    public int selectCount(CmRoleVO cmRoleVO) {
        return cmRoleMapper.selectCount(cmRoleVO);
    }

    @Override
    public List<CmRoleEntity> selectPageList(CmRoleVO cmRoleVO) {
        return cmRoleMapper.selectPageList(cmRoleVO);
    }

    @Override
    public boolean delete(CmRoleEntity cmRoleEntity) {
        int num = cmRoleMapper.delete(cmRoleEntity);
        return num == 1;
    }

    @Override
    public CmRoleEntity queryById(Long id) {
        return cmRoleMapper.queryById(id);
    }

    @Override
    public List<CmRoleEntity> queryAll() {
        return cmRoleMapper.queryAll();
    }

    @Override
    public List<CmRoleEntity> queryByUserId(Long userId) {
        return cmRoleMapper.queryByUserId(userId);
    }

}
