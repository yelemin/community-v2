package com.ylm.community.enums;

import com.ylm.community.model.base.BaseErrorCode;
import lombok.Getter;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
@Getter
public enum ErrorCode implements BaseErrorCode {

    /**
     *
     */
    SYSTEM_PARAM_ERROR(100001, "参数错误"),
    SYSTEM_JSON_PARSE_ERROR(100002, "JSON解析错误"),
    SYSTEM_PARAM_ERROR_TOO_LONG(100003, "字段过长"),
    SYSTEM_PARAM_ERROR_IS_NULL(100004, "字段为空"),
    SYSTEM_PARAM_ERROR_VALID_DATA_RANGE(100005, "字段不在有效的范围内"),
    SYSTEM_STRING_TO_RESPONSE_ERROR(100006, "接口请求返回内容转为Response失败"),
    SYSTEM_INNER_FALLBACK(100007, "系统错误"),
    SYSTEM_ERROR_FORMAT(100008, "数据格式错误"),
    SYSTEM_ERROR_PAGE_TOO_LARGE(100009, "分页数目太大"),
    SYSTEM_ERROR_NUMBER_ERROR(1000010, "数字输入错误"),
    SYSTEM_ERROR_NOTHING_SAVED(100011, "未能保存数据"),
    SYSTEM_ERROR_NOTHING_UPDATED(100012, "未能更新数据"),
    SYSTEM_ERROR_NOTHING_DELETED(100013, "未能删除数据"),
    SYSTEM_ERROR_ACCESS_OBJECT_IN_LOCK(100014, "访问过于频繁，请稍后再试"),
    SYSTEM_ERROR_LOCK_UN_ACQUIRED(100015, "未能获得锁"),
    SYSTEM_ERROR_NOT_FOUND_TARGET(100023, "未找到数据"),
    SYSTEM_ERROR_HAS_DATA(100024, "数据已存在"),
    SYSTEM_ERROR_UPLOAD_FAIL(100025, "上传文件失败，请重试"),
    SYSTEM_FILE_FORMAT_ILLEGAL(100026, "文件格式错误"),
    SYSTEM_UNRECOGNIZED_ERROR(100201, "未识别错误"),
    SYSTEM_ERROR(100301, "系统错误"),
    SYSTEM_SIG_ERROR(100302, "签名错误"),
    SYSTEM_APP_ID_ERROR(100303, "app-id 错误"),


    ;
    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private final int code;

    private final String msg;

}