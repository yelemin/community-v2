package com.ylm.community.model.base;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
public class CommunityException extends RuntimeException {

    @Getter
    private final int code;

    @Getter
    private final String customMessage;

    @Getter
    private final Object data;

    public CommunityException(BaseErrorCode code) {
        this(code, code.getMsg(), null);
    }

    public CommunityException(Throwable cause, BaseErrorCode code) {
        this(cause, code, null);
    }

    public CommunityException(Throwable cause, BaseErrorCode code, String message) {
        super(cause);
        this.code = code.getCode();
        this.customMessage = StringUtils.defaultString(message, code.getMsg());
        this.data = null;
    }

    public CommunityException(BaseErrorCode code, String customMessage) {
        this(code, customMessage, null);
    }

    public CommunityException(BaseErrorCode code, String customMessage, Object data) {
        this.code = code.getCode();
        this.customMessage = customMessage;
        this.data = data;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isNotBlank(this.customMessage)) {
            return this.customMessage;
        }
        return "[" + code + "]" + customMessage;
    }

}

