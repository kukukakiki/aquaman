package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.Dept;
import com.aquaman.security.admin.entity.vo.DeptVO;
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
public interface IDeptService extends IService<Dept> {

    /**
     * 根结点部门树
     */
    List<DeptVO> findDeptTreeVOByRoot();

}
