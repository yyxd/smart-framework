package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HinTi on 2019/7/8.
 * Goal: 基于Jackson 实现JSON和POJO之间的转换
 */
public class JsonUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // 将POJO转换为JSON
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert POJO to JSON failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    // 将JSON转换为POJO
    public static <T> T fromJson(String json,Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (Exception e) {
            LOGGER.error("convert JSON to POJO failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}