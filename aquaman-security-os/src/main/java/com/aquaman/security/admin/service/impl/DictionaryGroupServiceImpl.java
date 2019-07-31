package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.DictionaryGroup;
import com.aquaman.security.admin.entity.query.DictionaryGroupQuery;
import com.aquaman.security.admin.mapper.DictionaryGroupMapper;
import com.aquaman.security.admin.service.IDictionaryGroupService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
