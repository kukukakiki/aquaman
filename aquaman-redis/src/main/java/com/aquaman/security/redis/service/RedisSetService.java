package com.aquaman.security.redis.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
public interface RedisSetService {

    /**
     * 向redis放入key，value,指向字符串类型,且永久有效
     * 该方法入参都是{@link String}类型
     * @param key
     * @param value
     */
    void setKeyValueForEver(String key, String value);

    /**
     * 向redis放入key，value,且永久有效
     * 入参key:{@link String}类型
     * 入参value:{@link Object}类
     * @param key
     * @param value
     */
    void setKeyValueForEver(String key, Object value);

    /**
     * 向redis放入key，value,并制定过期时间,默认为毫秒计量单位
     * 入参key:{@link String}类型
     * 入参value:{@link Object}类
     * 入参timeout:{@link Long}类
     * @param key
     * @param value
     * @param timeout 超时时间
     */
     default void setKeyValue(String key, Object value, long timeout) {
         this.setKeyValue(key, value, timeout, TimeUnit.MILLISECONDS);
     }

    /**
     * 向redis放入key，value,并指定过期时间和时间的计量单位
     * 入参key:{@link String}类型
     * 入参value:{@link Object}类
     * 入参timeout:{@link Long}类
     * 入参unit:{@link TimeUnit}类
     * @param key
     * @param value
     * @param timeout 超时时间
     * @param unit 时间格式
     */
    void setKeyValue(String key, Object value, long timeout, TimeUnit unit);

    /**
     * 将redis的key替换成新的key
     * @param oldKey
     * @param newKey
     */
    void setRenameKey(String oldKey, String newKey);

    /**
     * 仅当新的key不存在时，将旧key改名为新key
     * @param oldKey
     * @param newKey
     * @return
     */
    Boolean setRenameNewKeyNotExists(String oldKey, String newKey);

    /**
     * 移除key的过期时间，key将持久保持
     * @param key
     * @return
     */
    Boolean setKeyForEver(String key);

    /**
     * 设置key的过期时间，并默认时间单位为毫秒
     * @param key
     * @param timeout
     * @return
     */
    default Boolean setExpire(String key, long timeout) {
        return this.setExpire(key, timeout, TimeUnit.MICROSECONDS);
    }

    /**
     * 设置key的过期时间
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    Boolean setExpire(String key, long timeout, TimeUnit unit);

    /**
     * 设置key的过期时间，给出具体过期日期
     * @param key
     * @param date
     * @return
     */
    Boolean setExpireByDate(String key, Date date);
}
