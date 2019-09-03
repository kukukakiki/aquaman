package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.DictionaryItem;
import com.aquaman.security.admin.entity.query.DictionaryItemQuery;
import com.aquaman.security.admin.mapper.DictionaryItemMapper;
import com.aquaman.security.admin.service.IDictionaryItemService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemMapper, DictionaryItem> implements IDictionaryItemService {

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;

    @Override
    public Optional<IPage<DictionaryItem>> findItemByGroupId(DictionaryItemQuery query) {
        Assert.notNull(query.getGroupId(), "字典目录ID不能为空");
        IPage<DictionaryItem> page = dictionaryItemMapper.selectPage(query, query.instanceQueryWrapper());
        return Optional.ofNullable(page);
    }

    @Override
    public Optional<IPage<DictionaryItem>> findItemByGroupId(Long groupId) {
        Assert.notNull(groupId, "字典目录ID不能为空");
        DictionaryItemQuery query = new DictionaryItemQuery();
        query.setGroupId(groupId);
        IPage<DictionaryItem> page = dictionaryItemMapper.selectPage(query, query.instanceQueryWrapper());
        return Optional.ofNullable(page);
    }
}
