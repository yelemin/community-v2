package com.ylm.community.enums;

import com.ylm.community.model.base.RespCodeMsg;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
public enum BaseRespCodeMsg implements RespCodeMsg {

    /**
     * 成功
     */
    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    SYSTEM_BAD_REQUEST(10400,"非法请求，参数错误"),
    SYSTEM_AUTH_FAIL(10401, "认证失败"),
    SYSTEM_ERROR(10500, "系统错误"),
    ;
    private int code;
    private String msg;

    BaseRespCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
