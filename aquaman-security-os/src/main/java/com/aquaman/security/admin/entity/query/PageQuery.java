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
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * 封装至Mybatis-plus中的Page<T>对象
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/3
 * @since 2019/3/3
 */
public class PageQuery<T> implements IPage<T> {

    private static final long serialVersionUID = -4445390117757525729L;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页
     */
    private long current = 1;
    /**
     * SQL 排序 ASC 数组
     */
    private String[] ascs;
    /**
     * SQL 排序 DESC 数组
     */
    private String[] descs;
    /**
     * 自动优化 COUNT SQL
     */
    private boolean optimizeCountSql = true;
    /**
     * 是否进行 count 查询
     */
    private boolean isSearchCount = true;

    public PageQuery() {
        // to do nothing
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public PageQuery(long current, long size) {
        this(current, size, 0);
    }

    public PageQuery(long current, long size, long total) {
        this(current, size, total, true);
    }

    public PageQuery(long current, long size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    public PageQuery(long current, long size, long total, boolean isSearchCount) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    /**
     * 是否存在上一页
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public PageQuery<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public PageQuery<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public PageQuery<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public PageQuery<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String[] ascs() {
        return ascs;
    }

    public PageQuery<T> setAscs(List<String> ascs) {
        if (CollectionUtils.isNotEmpty(ascs)) {
            this.ascs = ascs.toArray(new String[0]);
        }
        return this;
    }


    /**
     * 升序
     *
     * @param ascs 多个升序字段
     */
    public PageQuery<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    @Override
    public String[] descs() {
        return descs;
    }

    public PageQuery<T> setDescs(List<String> descs) {
        if (CollectionUtils.isNotEmpty(descs)) {
            this.descs = descs.toArray(new String[0]);
        }
        return this;
    }

    /**
     * 降序
     *
     * @param descs 多个降序字段
     */
    public PageQuery<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    public boolean isSearchCount() {
        if (total < 0) {
            return false;
        }
        return isSearchCount;
    }

    public PageQuery<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    public PageQuery<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

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
                    field.setAccessible(true);
                    if(StringUtils.equals("java.lang.String", field.getType().getCanonicalName())) {
                        String value = (String)field.get(this);
                        if(StringUtils.equals("", value)) {
                            field.set(this, null);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return new QueryWrapper(this);
    }
}
