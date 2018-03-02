package com.carejava.duce.ject.web.core;

import java.io.Serializable;

import java.util.List;

public class PageResultBeanList<T> implements Serializable {

    private int totalNum;
    private int totalPage;

    private List<T> list;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
