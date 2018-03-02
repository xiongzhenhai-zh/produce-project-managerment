package com.carejava.duce.ject.web.service;

import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;

import java.util.List;

public interface IProduceRecordService {
    boolean add(ProduceRecordEntity produceRecordEntity);

    boolean update(ProduceRecordEntity produceRecordEntity);

    int selectCount(ProduceRecordVO produceRecordVO);

    List<ProduceRecordEntity> selectPageList(ProduceRecordVO produceRecordVO);

    boolean delete(ProduceRecordEntity produceRecordEntity);


    ProduceRecordEntity queryById(Long id);
    List<ProduceRecordEntity> queryAll();
}
