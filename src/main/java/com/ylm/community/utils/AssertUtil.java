package com.ylm.community.utils;

import com.ylm.community.enums.ErrorCode;
import com.ylm.community.model.base.BaseErrorCode;
import com.ylm.community.model.base.CommunityException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
public final class AssertUtil {

    private AssertUtil() {
    }

    public static void notNull(Object object) {
        notNull(object, ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL);
    }

    public static void notNull(Object object, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        notNull(object, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void notNull(Object object, String message) {
        notNull(object, ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL, message);
    }

    public static void notNull(Object object, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (object == null) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isEmpty(Collection<?> col, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isEmpty(col, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isEmpty(Collection<?> col, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (CollectionUtils.isNotEmpty(col)) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isPositive(Long number) {
        isPositive(number, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void isPositive(Long number, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isPositive(number, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isPositive(Long number, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (number == null || number <= 0) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isPositive(Integer number) {
        isPositive(number, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void isPositive(Integer number, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isPositive(number, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isPositive(Integer number, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (number == null || number <= 0) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isNatural(Long number) {
        isNatural(number, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void isNatural(Long number, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isNatural(number, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isNatural(Long number, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (number == null || number < 0) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isNatural(Integer number) {
        isNatural(number, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void isNatural(Integer number, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isNatural(number, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isNatural(Integer number, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (number == null || number < 0) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void notEmpty(Collection<?> col) {
        notEmpty(col, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void notEmpty(Collection<?> col, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        notEmpty(col, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void notEmpty(Collection<?> col, String message) {
        assert message != null;
        notEmpty(col, ErrorCode.SYSTEM_PARAM_ERROR, message);
    }

    public static void notEmpty(Collection<?> col, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (CollectionUtils.isEmpty(col)) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void notEmpty(Map<?, ?> map, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        notEmpty(map, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void notEmpty(Map<?, ?> map, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (MapUtils.isEmpty(map)) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void notBlank(String str) {
        notBlank(str, ErrorCode.SYSTEM_PARAM_ERROR);
    }

    public static void notBlank(String str, String message) {
        assert StringUtils.isNotBlank(message);
        if (StringUtils.isBlank(str)) {
            throw new CommunityException(ErrorCode.SYSTEM_PARAM_ERROR, message);
        }
    }

    public static void notBlank(String str, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        notBlank(str, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void notBlank(String str, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        assert StringUtils.isNotBlank(message);
        if (StringUtils.isBlank(str)) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

    public static void isTrue(boolean condition, BaseErrorCode baseErrorCode) {
        assert baseErrorCode != null;
        isTrue(condition, baseErrorCode, baseErrorCode.getMsg());
    }

    public static void isTrue(boolean condition, BaseErrorCode baseErrorCode, String message) {
        assert baseErrorCode != null;
        if (!condition) {
            throw new CommunityException(baseErrorCode, message);
        }
    }

}

