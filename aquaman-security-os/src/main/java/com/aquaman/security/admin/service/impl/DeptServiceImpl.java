package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.Dept;
import com.aquaman.security.admin.entity.dto.DeptDTO;
import com.aquaman.security.admin.entity.query.DeptQuery;
import com.aquaman.security.admin.entity.vo.DeptVO;
import com.aquaman.security.admin.mapper.DeptMapper;
import com.aquaman.security.admin.service.IDeptService;
import com.aquaman.security.common.constant.AquamanConstant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptVO> findDeptTreeVOByRoot() {
        return this.recursionDept(null);
    }

    @Override
    public DeptDTO findDeptDTOByPrimaryKey(Long id) {
        DeptDTO deptDTO = new DeptDTO();
        // step1
        Dept dept = getById(id);
        // step2
        List<Dept> parentList = deptMapper.selectList(new DeptQuery().setType(dept.getType()).instanceQueryWrapper());
        // step3
        deptDTO.setParentList(parentList).setDept(dept);
        return deptDTO;
    }

    /**
     * TODO 编写递归组装部门树
     * @param deptQuery
     */
    private List<DeptVO> recursionDept(DeptQuery deptQuery) {
        if(deptQuery == null) {
            deptQuery = new DeptQuery();
        }
        if (deptQuery.getParentId() == null) {
            deptQuery.setParentId(AquamanConstant.DB_ROOT_NUM);
        }
        List<Dept> lists = deptMapper.selectList(deptQuery.instanceQueryWrapper());
        if(CollectionUtils.isNotEmpty(lists)) {
            List<DeptVO> deptVOList = new LinkedList<>();
            for(Dept dept : lists) {
                DeptVO deptVO = new DeptVO();
                // 将dept对象赋值到vo中
                BeanUtils.copyProperties(dept, deptVO);
                deptVOList.add(deptVO);
                // 递归查询所以父节点ID
                deptQuery.setParentId(dept.getId());
                // 递归查询下一节点集合
                deptVO.setChildren(this.recursionDept(deptQuery));
            }
            return deptVOList;
        }
        return null;
    }
}
