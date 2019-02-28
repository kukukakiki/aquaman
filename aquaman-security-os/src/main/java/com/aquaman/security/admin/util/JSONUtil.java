package com.aquaman.security.admin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
public class JSONUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换成JSON字符串
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToJSONString(Object o) throws JsonProcessingException {
        String str = objectMapper.writeValueAsString(o);
        return str;
    }
}
