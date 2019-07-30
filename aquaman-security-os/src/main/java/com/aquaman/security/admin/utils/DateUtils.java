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
package com.aquaman.security.admin.utils;

import io.jsonwebtoken.lang.Assert;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-07-30
 * @since 2019-07-30
 */
@UtilityClass
public class DateUtils {

    public static final transient String BASE_STYLE = "yyyy-MM-dd HH:mm:ss";

    public static final transient String UNSIGNED_STYLE = "yyyyMMddHHmmss";

    public static final transient String CHINA_STYLE = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 基础时间格式（例：2019-07-30 23:12:34）
     */
    private static final DateTimeFormatter formatterBase = DateTimeFormatter.ofPattern(BASE_STYLE);

    /**
     * 无符号时间格式（例：20190730231234）
     */
    private static final DateTimeFormatter formatterUnsigned = DateTimeFormatter.ofPattern(UNSIGNED_STYLE);

    /**
     * 中文时间格式（例：2019年07月30日 23时12分34秒）
     */
    private static final DateTimeFormatter formatterChind = DateTimeFormatter.ofPattern(CHINA_STYLE);

    /**
     * 获取当前时间无符号格式
     * @return
     */
    public static String currentUnsigned() {
        return formatterUnsigned.format(LocalDateTime.now());
    }

    /**
     * 根据自定义时间格式，获取当前时间
     * @param dateTimeFormat
     * @return
     */
    public static String customDateFormat(String dateTimeFormat) {
        Assert.notNull(dateTimeFormat, "自定义时间格式不能为空");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    /**
     * 入参为{@link Date}转换成基础时间格式
     * @param date
     * @return
     */
    public static String baseDateTime(Date date) {
        return baseDateTime(dateToLocalDateTime(date));
    }

    /**
     *
     * @param localDateTime
     * @return
     */
    public static String baseDateTime(LocalDateTime localDateTime) {
        return formatterBase.format(localDateTime);
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
}
