package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.DictionaryGroup;
import com.aquaman.security.admin.entity.domain.DictionaryItem;
import com.aquaman.security.admin.entity.query.DictionaryGroupQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.DeleteEnum;
import com.aquaman.security.admin.exception.ValidatorException;
import com.aquaman.security.admin.mapper.DictionaryGroupMapper;
import com.aquaman.security.admin.service.IDictionaryGroupService;
import com.aquaman.security.admin.service.IDictionaryItemService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
@Service
public class DictionaryGroupServiceImpl extends ServiceImpl<DictionaryGroupMapper, DictionaryGroup> implements IDictionaryGroupService {

    @Autowired
    private IDictionaryItemService dictionaryItemService;

    @Override
    public void deleteDictionaryGroup(Long id) {
        IPage<DictionaryItem> page1 = dictionaryItemService.findItemByGroupId(id).get();
        if(page1 != null && CollectionUtils.isNotEmpty(page1.getRecords()) && page1.getRecords().size() > 0) {
            throw new ValidatorException(new ResultVO(ResultCodeEnum.DICTIONARY_GROUP_DELETE_HAVE_ITEM_ERROR));
        }
        DictionaryGroup dictionaryGroup = new DictionaryGroup().setId(id).setIsDeleted(DeleteEnum.YES.getValue());
        updateById(dictionaryGroup);
    }
}
