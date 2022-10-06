package com.ylm.community.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
@Slf4j
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <T, R> R copyBean(T obj, Class<R> rClass) {
        if (obj == null) {
            return null;
        }
        try {
            R r = rClass.getConstructor().newInstance();
            copyProperties(obj, r);
            return r;
        } catch (Exception e) {
            log.error("bean copy error", e);
            throw new RuntimeException(e);
        }
    }

    public static <T, R> R copyBean(T obj, Supplier<R> beanCreator) {
        return copyBean(obj, beanCreator, null);
    }

    public static <T, R> R copyBean(T obj, Supplier<R> beanCreator, BiConsumer<T, R> action) {
        if (obj == null) {
            return null;
        }
        R r = beanCreator.get();
        copyProperties(obj, r);
        if (action != null) {
            action.accept(obj, r);
        }
        return r;
    }

    public static <T, R> List<R> copyList(List<T> list, Class<R> rClass) {
        return copyList(list, rClass, null);
    }

    public static <T, R> List<R> copyList(List<T> list, Class<R> rClass, BiConsumer<T, R> action) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        try {
            Constructor<R> constructor = rClass.getConstructor();
            return copyList(list, () -> {
                try {
                    return constructor.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, action);
        } catch (Exception e) {
            log.error("collection copy error", e);
            throw new RuntimeException(e);
        }
    }

    public static <T, R> List<R> copyList(List<T> list, Supplier<R> beanCreator, BiConsumer<T, R> action) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(v -> copyBean(v, beanCreator, action)).collect(Collectors.toList());
    }

}
