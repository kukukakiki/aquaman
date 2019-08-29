package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.query.PageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/31
 * @since 2019/7/31
 */
public interface IAquamanService<T> extends IService<T> {

    /**
     * 简化分页带参数查询
     * @param query
     * @return
     */
    default IPage<T> pageByQuery(PageQuery query) {
        QueryWrapper queryWrapper = query.instanceQueryWrapper();
        // 默认采用gmt_create倒序
        queryWrapper.orderByDesc("gmt_create");
        return page(query, queryWrapper);
    }
}
