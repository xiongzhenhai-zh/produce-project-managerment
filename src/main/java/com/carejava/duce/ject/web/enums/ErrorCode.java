package com.carejava.duce.ject.web.enums;

/**
 * 错误码枚举<br>
 * 错误码枚举
 *
 * @author zhenhai.xiong
 * @version [V1.0, 2017年11月06日]
 */
public class ErrorCode {
    public final static ErrorCode SUCCESS = new ErrorCode("000", "操作成功");
    public final static ErrorCode FAIL_NOT_LOGIN = new ErrorCode("111", "您还没有登录");
    public final static ErrorCode ERR_INVALID_PARAMS = new ErrorCode("222", "参数非法");
    public final static ErrorCode ERR_DECRYPT = new ErrorCode("333", "解密失败");
    public final static ErrorCode FAIL_LOGIN = new ErrorCode("444", "登陆失败");
    public final static ErrorCode NO_AUTHORITY_ERR = new ErrorCode("888", "没有该操作的权限");
    public final static ErrorCode ERR_SYSTEM_ERR = new ErrorCode("999", "系统异常");
    public final static ErrorCode NOT_EXISTS = new ErrorCode("1000", "结果不存在");
    public final static ErrorCode IMAGE_UPLOAD_FAILED = new ErrorCode("2000", "文件上传失败");
    public final static ErrorCode READ_EXCEL_FAILED = new ErrorCode("2001", "读取excel异常");
    public final static ErrorCode EXCEL_IS_EMPTY = new ErrorCode("2003", "excel内容为空");
    public final static ErrorCode PROPERTY_EXIST = new ErrorCode("3000", "记录已经存在");

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDesc;

    ErrorCode(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public final static String getDesc(String errorCode) {
        if (errorCode.equals(SUCCESS.getErrorCode())) {
            return SUCCESS.getErrorDesc();
        } else if (errorCode.equals(FAIL_NOT_LOGIN.getErrorCode())) {
            return FAIL_NOT_LOGIN.getErrorDesc();
        } else if (errorCode.equals(ERR_INVALID_PARAMS.getErrorCode())) {
            return ERR_INVALID_PARAMS.getErrorDesc();
        } else if (errorCode.equals(ERR_DECRYPT.getErrorCode())) {
            return ERR_DECRYPT.getErrorDesc();
        } else if (errorCode.equals(ERR_SYSTEM_ERR.getErrorCode())) {
            return ERR_SYSTEM_ERR.getErrorDesc();
        }

        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
