/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@UtilityClass
@Slf4j
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

    /**
     * 解析JSON对象中包含数组
     * @param JOSNStr
     * @param t
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T resolverObjectIncludeArrayByJSON(String JOSNStr, Class<T> t) throws NoSuchFieldException, IOException, IllegalAccessException, InstantiationException {
        // 将JSON字符串转换成JsonNode格式
        JsonNode jsonNode = objectMapper.readTree(JOSNStr);
        Iterator<Map.Entry<String, JsonNode>> elements = jsonNode.fields();
        // 实例化返回对象
        T clazz = t.newInstance();
        // 遍历JSONNode节点数据
        while(elements.hasNext()) {
            Map.Entry<String, JsonNode> node = elements.next();
            // 获取当前节点的key值
            String key = node.getKey();
            // 反射传入转换对象的key值对应的属性
            boolean exist = false;
            Field[] fields= t.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if(fields[i].getName().equals(key))
                {
                    exist = true;
                    break;
                }
            }
            if(exist){
                Field field = clazz.getClass().getDeclaredField(key);
                field.setAccessible(true);
                // 获取当前key值对应的类
                Class<?> cls = t.getDeclaredField(key).getType();
                // 判断是否为集合对象
                if(StringUtils.equals(cls.getName(), "java.util.List")) {
                    ParameterizedType p = (ParameterizedType)t.getDeclaredField(key).getGenericType();
                    Class c = (Class)p.getActualTypeArguments()[0];
                    // 集合对象只用Array进行实例化
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    String jsonPase = node.getValue().toString();
                    if(StringUtils.equals(jsonPase.substring(0, 1), "{") && StringUtils.equals(jsonPase.substring(jsonPase.length() - 1), "}")){
                        jsonPase = "[" + jsonPase + "]";
                    }
                    Object array = objectMapper.readValue(jsonPase, Array.newInstance(c, 1).getClass());
                    // 遍历Array，并且set到返回对象对应的属性中
                    if(array != null && Array.getLength(array) > 0) {
                        List list = new ArrayList();
                        for(int i = 0; i < Array.getLength(array); i++){
                            list.add(Array.get(array, i));
                        }
                        field.set(clazz, list);
                    }
                } else {
                    // 转换的对象set到返回对象的属性中
                    Object obj = objectMapper.readValue(node.getValue().toString(), cls);
                    field.set(clazz, obj);
                }
            } else {
                log.error("JSON转换对象属性[{}]不存在跳过转换", key);
            }

        }
        return clazz;
    }
}
