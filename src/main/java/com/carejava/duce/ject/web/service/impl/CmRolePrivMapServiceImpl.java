package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.CmRolePrivMapMapper;
import com.carejava.duce.ject.web.entity.CmRolePrivMapEntity;
import com.carejava.duce.ject.web.in.vo.CmRolePrivMapVO;
import com.carejava.duce.ject.web.service.ICmRolePrivMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * CmRolePrivMap Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("cmRolePrivMapService")
public class CmRolePrivMapServiceImpl implements ICmRolePrivMapService{

    @Autowired
    private CmRolePrivMapMapper cmRolePrivMapMapper;
    
    @Override
    public boolean add(CmRolePrivMapEntity cmRolePrivMapEntity) {
        int num = cmRolePrivMapMapper.insert(cmRolePrivMapEntity);
        return num == 1;
    }

    @Override
    public boolean update(CmRolePrivMapEntity cmRolePrivMapEntity) {
        int num = cmRolePrivMapMapper.update(cmRolePrivMapEntity);
        return num == 1;
    }

    @Override
    public int selectCount(CmRolePrivMapVO cmRolePrivMapVO) {
        return cmRolePrivMapMapper.selectCount(cmRolePrivMapVO);
    }

    @Override
    public List<CmRolePrivMapEntity> selectPageList(CmRolePrivMapVO cmRolePrivMapVO) {
        return cmRolePrivMapMapper.selectPageList(cmRolePrivMapVO);
    }

    @Override
    public boolean delete(CmRolePrivMapEntity cmRolePrivMapEntity) {
        int num = cmRolePrivMapMapper.delete(cmRolePrivMapEntity);
        return num == 1;
    }

    @Override
    public CmRolePrivMapEntity queryById(Long id) {
        return cmRolePrivMapMapper.queryById(id);
    }

    @Override
    public List<CmRolePrivMapEntity> queryAll() {
        return cmRolePrivMapMapper.queryAll();
    }

}
