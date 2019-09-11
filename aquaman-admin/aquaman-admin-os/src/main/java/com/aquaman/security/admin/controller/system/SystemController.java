package com.aquaman.security.admin.controller.system;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.entity.domain.System;
import com.aquaman.security.admin.entity.dto.RoleDTO;
import com.aquaman.security.admin.entity.query.SystemQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.DeleteEnum;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.ISystemService;
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
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/system")
@Slf4j
public class SystemController extends BaseController {


    @Autowired
    private ISystemService systemService;

    /**
     * 分页列表查询
     * @param query
     * @return
     */
    @GetMapping
    public ResultVO<IPage<System>> getByPage(SystemQuery query) {
        IPage<System> page1 = systemService.pageByQuery(query);
        ResultVO<IPage<System>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    @GetMapping("/all")
    public ResultVO<List<System>> getAll() {
        List<System> list = systemService.query().list();
        ResultVO<List<System>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
    }

    /**
     * 根据ID查询详情
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResultVO<System> getById(@PathVariable Long id) {
        System system = systemService.getById(id);
        ResultVO<System> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, system);
        return resultVO;
    }

    /**
     * 系统新增
     * @param system
     * @return
     */
    @PostMapping
    public ResultVO save(System system) {
        boolean isSuccess = systemService.save(system);
        if (!isSuccess) {
            log.warn("新增系统失败，执行返回结果：", isSuccess);
            return error(ResultCodeEnum.ROLE_SAVE_ERROR);
        }
        return success();
    }

    /**
     * 系统修改
     * @param system
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResultVO update(System system){
        boolean isSuccess = systemService.updateById(system);
        if(!isSuccess){
            log.warn("修改系统失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 系统删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id){
        boolean isSuccess = systemService.removeById(id);
        if(!isSuccess){
            log.warn("删除系统失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
