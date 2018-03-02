package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.CmPrivMapper;
import com.carejava.duce.ject.web.entity.CmPrivEntity;
import com.carejava.duce.ject.web.in.vo.CmPrivVO;
import com.carejava.duce.ject.web.service.ICmPrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * CmPriv Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
@Service("cmPrivService")
public class CmPrivServiceImpl implements ICmPrivService{

    @Autowired
    private CmPrivMapper cmPrivMapper;
    
    @Override
    public boolean add(CmPrivEntity cmPrivEntity) {
        int num = cmPrivMapper.insert(cmPrivEntity);
        return num == 1;
    }

    @Override
    public boolean update(CmPrivEntity cmPrivEntity) {
        int num = cmPrivMapper.update(cmPrivEntity);
        return num == 1;
    }

    @Override
    public int selectCount(CmPrivVO cmPrivVO) {
        return cmPrivMapper.selectCount(cmPrivVO);
    }

    @Override
    public List<CmPrivEntity> selectPageList(CmPrivVO cmPrivVO) {
        return cmPrivMapper.selectPageList(cmPrivVO);
    }

    @Override
    public boolean delete(CmPrivEntity cmPrivEntity) {
        int num = cmPrivMapper.delete(cmPrivEntity);
        return num == 1;
    }

    @Override
    public CmPrivEntity queryById(Long id) {
        return cmPrivMapper.queryById(id);
    }

    @Override
    public List<CmPrivEntity> queryAll() {
        return cmPrivMapper.queryAll();
    }

}
