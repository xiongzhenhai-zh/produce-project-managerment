package com.carejava.duce.ject.web.service.impl;

import java.util.List;

import com.carejava.duce.ject.web.dao.ProduceRecordMapper;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;
import com.carejava.duce.ject.web.service.IProduceRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * ProduceRecord Service层
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2018年01月31日]
 */
@Service("produceRecordService")
public class ProduceRecordServiceImpl implements IProduceRecordService{

    private final static Logger LOGGER = LoggerFactory.getLogger(ProduceRecordServiceImpl.class);

    @Autowired
    private ProduceRecordMapper produceRecordMapper;
    
    @Override
    public boolean add(ProduceRecordEntity produceRecordEntity) {
        int num = produceRecordMapper.insert(produceRecordEntity);
        return num == 1;
    }

    @Override
    public boolean update(ProduceRecordEntity produceRecordEntity) {
        int num = produceRecordMapper.update(produceRecordEntity);
        return num == 1;
    }

    @Override
    public int selectCount(ProduceRecordVO produceRecordVO) {
        return produceRecordMapper.selectCount(produceRecordVO);
    }

    @Override
    public List<ProduceRecordEntity> selectPageList(ProduceRecordVO produceRecordVO) {
        return produceRecordMapper.selectPageList(produceRecordVO);
    }

    @Override
    public boolean delete(ProduceRecordEntity produceRecordEntity) {
        int num = produceRecordMapper.delete(produceRecordEntity);
        return num == 1;
    }
    @Override
    public ProduceRecordEntity queryById(Long id) {
        return produceRecordMapper.queryById(id);
    }
    @Override
    public List<ProduceRecordEntity> queryAll() {
        return produceRecordMapper.queryAll();
    }

}
