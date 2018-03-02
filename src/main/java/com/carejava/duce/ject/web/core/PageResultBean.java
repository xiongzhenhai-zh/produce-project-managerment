package com.carejava.duce.ject.web.core;

import java.io.Serializable;

import com.carejava.duce.ject.web.enums.ErrorCode;

public class PageResultBean extends ResultBean implements Serializable {

    private int totalNum;
    private int totalPage;

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

    public PageResultBean() {
        super();
    }

    public PageResultBean(String code, String desc, Serializable content) {
        super(code, desc, content);
    }

    public PageResultBean(ErrorCode errorCode) {
        this(errorCode.getErrorCode(), errorCode.getErrorDesc(), null);
    }

    public PageResultBean(ErrorCode errorCode, Serializable content) {
        this(errorCode.getErrorCode(), errorCode.getErrorDesc(), content);
    }

}
