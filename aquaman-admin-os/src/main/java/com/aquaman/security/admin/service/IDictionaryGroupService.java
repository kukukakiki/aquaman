package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.DictionaryGroup;
import com.aquaman.security.admin.entity.query.DictionaryGroupQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
public interface IDictionaryGroupService extends IAquamanService<DictionaryGroup> {

    /**
     * 删除字典组
     * 删除前需要校验该字典组所属的字典项是否有值,如果有则无法删除
     * @param id
     */
    void deleteDictionaryGroup(Long id);

}
