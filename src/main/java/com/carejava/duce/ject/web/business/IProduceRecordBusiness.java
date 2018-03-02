package com.carejava.duce.ject.web.business;

import com.carejava.duce.ject.web.core.ResultBean;
import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;

import javax.servlet.http.HttpServletRequest;

public interface IProduceRecordBusiness {
    ResultBean add(ProduceRecordEntity produceRecordEntity);

    ResultBean update(ProduceRecordEntity produceRecordEntity);

    ResultBean selectPageList(HttpServletRequest request, ProduceRecordVO produceRecordVO);

    ResultBean delete(HttpServletRequest request, ProduceRecordEntity produceRecordEntity);

    ResultBean queryById(Long id);
}
