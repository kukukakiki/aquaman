package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.DeptUser;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.DeptUserQuery;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.mapper.DeptUserMapper;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IDeptService;
import com.aquaman.security.admin.service.IDeptUserService;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
@Service
public class DeptUserServiceImpl extends ServiceImpl<DeptUserMapper, DeptUser> implements IDeptUserService {

    @Autowired
    private DeptUserMapper deptUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUserListByDeptId(Long deptId) {
        DeptUserQuery query = new DeptUserQuery();
        DeptUser deptUser = deptUserMapper.selectOne(query.instanceQueryWrapper());
        if(deptUser != null && StringUtils.isNotBlank(deptUser.getUserIds())) {
            String[] userIds = deptUser.getUserIds().split(",");
            Long[] ids = (Long[])ConvertUtils.convert(userIds, Long.class);
            List<User> userList = userMapper.selectBatchIds(Arrays.asList(ids));
            return userList;
        }
        return null;
    }
}
