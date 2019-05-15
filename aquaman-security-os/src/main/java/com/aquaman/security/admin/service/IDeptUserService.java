package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.DeptUser;
import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
public interface IDeptUserService extends IService<DeptUser> {

    /**
     * 通过部门ID获取用户集合
     * @param deptId
     * @return
     */
    List<User> queryUserListByDeptId(Long deptId);
}
