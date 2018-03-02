package com.carejava.duce.ject.web.enums;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年11月06日]
 */
public enum ProduceTypeEnums {

    BYDB(1,"通过数据库反向生成"),
    BYMODEL(2,"通过model生成");

    private Integer type;

    private String desc;

    ProduceTypeEnums(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static ProduceTypeEnums getEnumByType(Integer type){
        if (type == null){
            return BYDB;
        }
        for (ProduceTypeEnums e: ProduceTypeEnums.values()) {
            if (e.getType().equals(type)){
                return e;
            }
        }
        return BYDB;
    }
}
