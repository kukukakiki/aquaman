package com.aquaman.security.admin.controller.dept;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Dept;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.dto.DeptDTO;
import com.aquaman.security.admin.entity.vo.DeptVO;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IDeptService;
import com.aquaman.security.admin.service.IUserService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResultVO<List<DeptVO>> getAllByDeptTree() {
        List<DeptVO> items = deptService.findDeptTreeVOByRoot();
        ResultVO<List<DeptVO>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, items);
        return resultVO;
    }

    @GetMapping("/{id:\\d+}")
    public ResultVO<DeptDTO> get(@PathVariable Long id) {
        DeptDTO dept = deptService.findDeptDTOByPrimaryKey(id);
        // 组装成功信息
        ResultVO<DeptDTO> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, dept);
        return resultVO;
    }

    @GetMapping("/user_page/{deptId:\\d+}")
    public ResultVO<List<User>> getUserPageByDeptId(@PathVariable Long deptId) {
        IPage<User> page1 = userService.findUserByDeptId(deptId);
        ResultVO<List<User>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    @PostMapping
    public ResultVO save(Dept dept) {
        deptService.save(dept);
        return success();
    }

    @PutMapping("/{id:\\d+}")
    public ResultVO update(Dept dept) {
        deptService.updateById(dept);
        return success();
    }
}
