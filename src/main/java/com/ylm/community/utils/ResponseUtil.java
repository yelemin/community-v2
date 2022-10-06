package com.ylm.community.utils;

import com.ylm.community.enums.BaseRespCodeMsg;
import com.ylm.community.model.base.RespCodeMsg;
import com.ylm.community.model.base.Response;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
public class ResponseUtil {

    public static final Response<Boolean> OK = ResponseUtil.makeSuccess(true);

    public static <T> boolean isOk(Response<T> response) {
        return response != null && response.getCode() == 0;
    }

    public static <T> boolean isNotOk(Response<T> response) {
        return response == null || response.getCode() != 0;
    }

    public static <T> Response<T> makeResponse(int code, String msg, T obj) {
        return new Response<>(code, msg, obj);
    }

    public static <T> Response<T> makeResponse(int code, String msg) {
        return makeResponse(code, msg, null);
    }

    public static <T> Response<T> makeError(RespCodeMsg respCodeMsg) {
        return makeResponse(respCodeMsg.getCode(), respCodeMsg.getMsg(), null);
    }

    public static <T> Response<T> makeError(RespCodeMsg respCodeMsg, T obj) {
        return makeResponse(respCodeMsg.getCode(), respCodeMsg.getMsg(), obj);
    }

    public static <T> Response<T> makeSuccess(T obj) {
        return makeResponse(BaseRespCodeMsg.SUCCESS.getCode(), "", obj);
    }

    public static <T> Response<T> makeFail(String message) {
        return makeResponse(BaseRespCodeMsg.FAIL.getCode(), message);
    }

}
