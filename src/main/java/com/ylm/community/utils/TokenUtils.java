package com.ylm.community.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author flyingwhale
 * @date 2022/8/4
 */
@Slf4j
public class TokenUtils {

    /**
     * 不需要参加签名的参数：
     * sign
     */
    private static final String SIGN_PARAMS = "sign";

    /**
     * 解析token
     * @param token 令牌
     * @param aesKey 加密key
     * @param signKey 加签key
     * @return 解析map
     */
    public static Map<String, String> parseToken(String token, String aesKey, String signKey){
        String decrypt = "";
        try {
            decrypt = EncodeUtils.AES.decodeStringContent(token, aesKey);
        } catch (Exception e) {
            log.warn("TokenUtils parseToken aesDecrypt error! token:{}", token, e);
        }
        if (StringUtils.isBlank(decrypt)) {
            log.error("TokenUtils parseToken decrypt is null! token:{}", token);
            return null;
        }
        Map<String, String> rMap = JsonUtils.parseMap(decrypt, String.class);
        if (!verifySign(rMap, signKey)){
            log.error("TokenUtils parseToken token has been tampered! token:{}", token);
            return null;
        }
        // 验签通过后解析token
        return rMap;
    }


    /**
     * MD5 计算签名
     * @param baseStr 原始加签串
     * @param signKey 加签key
     * @return 拼接加签key后的串
     */
    public static final String sign(String baseStr, String signKey) {
        String str2Sign = baseStr + "&key=" + signKey;
        log.info("TokenUtils sign str2Sign:{}", str2Sign);
        return EncodeUtils.Md5.md5Hex(str2Sign).toUpperCase();
    }

    /**
     * 验签
     * @param rMap
     * @param signKey
     * @return
     */
    public static boolean verifySign(Map<String, String> rMap, String signKey){
        String parseSign = sign(generateSignSource(rMap), signKey);
        return StringUtils.equals(parseSign, rMap.get(SIGN_PARAMS));
    }

    /**
     * 对http请求参数作字典排序，拼接字符串
     *
     * @param paramMap
     * @return
     */
    public static String generateSignSource(Map<String, String> paramMap) {
        // 参数名
        Set<String> params = paramMap.keySet();
        List<String> sortedParams = new ArrayList<>(params);
        Collections.sort(sortedParams);
        StringBuilder sb = new StringBuilder();
        for (String paramKey : sortedParams) {
            if (SIGN_PARAMS.equals(paramKey)){
                // 跳过sign值
                continue;
            }
            // 参数值
            String value = paramMap.get(paramKey);
            if (StringUtils.isNotBlank(value)) {
                // &key=value
                sb.append("&").append(paramKey).append('=').append(value);
                log.debug("append {}={}", paramKey, value);
            }

        }
        String baseStr = sb.toString();
        // 去掉第一个&
        if (StringUtils.isNotBlank(baseStr)) {
            baseStr = baseStr.substring(1);
        }
        log.debug("baseStr={}", baseStr);
        return baseStr;
    }

    public static String makeToken(Map<String, String> paramMap, String aesKey, String signKey) {
        String baseStr = generateSignSource(paramMap);
        // 加签
        String sign = sign(baseStr, signKey);
        Map<String, String> resultMap = new HashMap<String, String>(paramMap);
        resultMap.put(SIGN_PARAMS, sign);
        String content = JsonUtils.toJsonString(resultMap);
        String token = null;
        try {
            token = EncodeUtils.AES.encodeStringContentAndConvertBase64(content, aesKey);
        } catch (Exception e) {
            log.error("TokenUtils makeToken aesSafeEncrypt error! baseStr:{}", baseStr, e);
        }
        if (StringUtils.isBlank(token)) {
            log.error("TokenUtils makeToken token is null! baseStr:{}", baseStr);
        }
        return token;
    }

    /**
     * 判断核心解析后的token信息是否非法
     * @param paramMap 解析后的核心token信息
     * @return 是否非法
     */
    public static boolean tokenIsInvalid(Map<String, String> paramMap) {
        if (Objects.isNull(paramMap)){
            return true;
        }
        return paramMap.containsValue(null);
    }

    /**
     * 判断核心解析后的token信息是否过期
     * @param paramMap 解析后的核心token信息
     * @param expirationTimeParam 过期时间参数名
     * @return 是否过期
     */
    public static boolean tokenIsExpire(Map<String, String> paramMap, String expirationTimeParam) {
        if (tokenIsInvalid(paramMap)) {
            return true;
        }
        long expirationTime = Long.parseLong(paramMap.get(expirationTimeParam));
        return expirationTime < System.currentTimeMillis();
    }

}
