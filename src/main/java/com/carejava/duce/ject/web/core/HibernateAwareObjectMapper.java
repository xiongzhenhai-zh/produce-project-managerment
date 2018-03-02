package com.carejava.duce.ject.web.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 实体与json映射<br>
 * 实体转换为json字符串
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    /**
     * 使用父类方法改写构造
     */
    public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
    }

}
