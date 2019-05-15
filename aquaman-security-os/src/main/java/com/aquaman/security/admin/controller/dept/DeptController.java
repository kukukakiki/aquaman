package com.aquaman.security.admin.controller.dept;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Dept;
import com.aquaman.security.admin.entity.vo.DeptVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IDeptService;
import com.aquaman.security.common.enums.ResultCodeEnum;
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

    @GetMapping("/all")
    public ResultVO<List<DeptVO>> getAllByDeptTree() {
        try {
            List<DeptVO> items = deptService.findDeptTreeVOByRoot();
            ResultVO<List<DeptVO>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, items);
            return resultVO;
        } catch (Exception e) {
            log.error("参数信息：{}；错误信息：{}", null, e.getMessage());
            return error(ResultCodeEnum.DEPT_TREE_GET_ALL_ERROR);
        }
    }

    @PostMapping
    public ResultVO save(Dept dept) {
        try {
            deptService.save(dept);
            return success();
        } catch (Exception e) {
            log.error("参数信息：{}；错误信息：{}", dept, e.getMessage());
            return error(ResultCodeEnum.DEPT_SAVE_ERROR);
        }
    }

    @PutMapping("/{id:\\d+}")
    public ResultVO update(@PathVariable Long id, Dept dept) {
        try {
            dept.setId(id);
            deptService.updateById(dept);
            return success();
        } catch (Exception e) {
            log.error("参数信息：{}；错误信息：{}", dept, e.getMessage());
            return error(ResultCodeEnum.DEPT_UPDATE_ERROR);
        }
    }
}
