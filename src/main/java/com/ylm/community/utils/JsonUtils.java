package com.ylm.community.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
@Slf4j
public final class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setTimeZone(TimeZone.getDefault());
    }

    public static <T> String toJsonString(T t) {
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String json, Class<T> t) {
        try {
            return OBJECT_MAPPER.readValue(json, t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String json, Class<T> parametrized, Class<?>... parameterClasses) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Map<String, T> parseMap(String json, Class<T> t) {
        try {
            return OBJECT_MAPPER.readValue(
                    json,
                    TypeFactory.defaultInstance().constructParametricType(Map.class, String.class, t)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, T> Map<K, T> parseMap(String json, Class<K> k, Class<T> t) {
        try {
            return OBJECT_MAPPER.readValue(
                    json,
                    TypeFactory.defaultInstance().constructParametricType(Map.class, k, t)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> parseObjectList(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(
                    json,
                    TypeFactory.defaultInstance().constructParametricType(ArrayList.class, clazz)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> HashSet<T> parseSet(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(
                    json,
                    TypeFactory.defaultInstance().constructParametricType(HashSet.class, clazz)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ----------------------------- JsonNode -----------------------------

    public static JsonNode read(String content) {
        if (StringUtils.isBlank(content)){
            return null;
        }
        try {
            JsonNode node = OBJECT_MAPPER.readTree(content);
            return node;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static ObjectNode newObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static boolean hasKey(JsonNode jsonNode, String key) {
        return Objects.nonNull(jsonNode) && jsonNode.has(key);
    }

    public static Integer getInt(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).asInt() : null;
    }

    public static Integer getInt(JsonNode jsonNode, String key, Integer defaultValue) {
        return Optional.ofNullable(getInt(jsonNode, key)).orElse(defaultValue);
    }

    public static Long getLong(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).asLong() : null;
    }

    public static Long getLong(JsonNode jsonNode, String key, Long defaultValue) {
        return Optional.ofNullable(getLong(jsonNode, key)).orElse(defaultValue);
    }

    public static Boolean getBoolean(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).asBoolean() : null;
    }

    public static Boolean getBoolean(JsonNode jsonNode, String key, Boolean defaultValue) {
        return Optional.ofNullable(getBoolean(jsonNode, key)).orElse(defaultValue);
    }

    public static String getString(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).asText() : null;
    }

    public static String getString(JsonNode jsonNode, String key, String defaultValue) {
        return Optional.ofNullable(getString(jsonNode, key)).orElse(defaultValue);
    }

    public static Double getDouble(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).asDouble() : null;
    }

    public static Double getDouble(JsonNode jsonNode, String key, Double defaultValue) {
        return Optional.ofNullable(getDouble(jsonNode, key)).orElse(defaultValue);
    }

    public static String getJsonString(JsonNode jsonNode, String key) {
        return hasKey(jsonNode, key) ? jsonNode.get(key).toString() : null;
    }
}