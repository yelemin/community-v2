package com.ylm.community.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
public final class CommonConstants {

    private CommonConstants(){}

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 用户的AES的key
     */
    public static final String ACCOUNT_AES_KEY = "jmfeF18He0drgMjScfgY/g==";

    /**
     * 用户的sign的key
     */
    public static final String ACCOUNT_SIGN_KEY = "2412bb7f2e8548bf8f0c4894756eee46";


    /**
     * 授权令牌过期时间（单位：秒）
     */
    public static final Long AUTH_TOKEN_EXPIRES_IN = 12 * 60 * 60L;

}
