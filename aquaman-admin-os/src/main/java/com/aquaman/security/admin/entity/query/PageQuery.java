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
package com.aquaman.security.admin.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 封装至Mybatis-plus中的Page<T>对象
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/3
 * @since 2019/3/3
 */
public class PageQuery<T> extends Page<T> {

    private static final long serialVersionUID = -4445390117757525729L;

    /**
     * 改造实例化QueryWrapper方法
     * @return
     */
    public QueryWrapper<T> instanceQueryWrapper() {
        // 调用Mybatis-plus底层分页方法需要QueryWrapper对象，此处为了方便实例化查询对象
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields) {
                try {
                    if(field != null) {
                        field.setAccessible(true);
                        if(StringUtils.equals("java.lang.String", field.getType().getCanonicalName())) {
                            String value = (String) field.get(this);
                            if(StringUtils.equals("", value)) {
                                field.set(this, null);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryWrapper queryWrapper = new QueryWrapper(this);
        // 默认采用gmt_create倒序
        // queryWrapper.orderByDesc("gmt_create");
        return queryWrapper;
    }
}
