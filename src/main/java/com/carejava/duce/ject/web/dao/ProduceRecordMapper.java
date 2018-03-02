package com.carejava.duce.ject.web.dao;

import java.util.List;

import com.carejava.duce.ject.web.entity.ProduceRecordEntity;
import com.carejava.duce.ject.web.in.vo.ProduceRecordVO;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2018年01月31日]
 */
public interface ProduceRecordMapper {
    
    int insert(ProduceRecordEntity produceRecordEntity);
    
    int update(ProduceRecordEntity produceRecordEntity);
    
    int selectCount(ProduceRecordVO produceRecordVO);
    
    List<ProduceRecordEntity> selectPageList(ProduceRecordVO produceRecordVO);
    
    int delete(ProduceRecordEntity produceRecordEntity);

    ProduceRecordEntity queryById(Long id);
    List<ProduceRecordEntity> queryAll();

}
