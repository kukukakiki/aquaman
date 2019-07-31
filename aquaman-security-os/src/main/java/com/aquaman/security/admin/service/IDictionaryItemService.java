package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.DictionaryItem;
import com.aquaman.security.admin.entity.query.DictionaryItemQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
public interface IDictionaryItemService extends IAquamanService<DictionaryItem> {

    /**
     * 根据字典目录ID查询所属子项集合
     * @param query
     * @return
     */
    Optional<IPage<DictionaryItem>> findItemByGroupId(DictionaryItemQuery query);



}
