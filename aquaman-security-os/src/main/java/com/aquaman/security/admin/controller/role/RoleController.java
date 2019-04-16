package com.aquaman.security.admin.controller.role;import com.aquaman.security.admin.controller.base.BaseController;import com.aquaman.security.admin.entity.domain.Role;import com.aquaman.security.admin.entity.domain.User;import com.aquaman.security.admin.entity.query.RoleQuery;import com.aquaman.security.admin.entity.vo.ResultVO;import com.aquaman.security.admin.enums.StatusEnum;import com.aquaman.security.admin.service.IRoleService;import com.aquaman.security.common.enums.ResultCodeEnum;import com.baomidou.mybatisplus.core.metadata.IPage;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import java.util.List;/** * 角色管理Rest * * @author wei wang * @since 2019-03-04 */@Slf4j@RestController@RequestMapping("/role")public class RoleController extends BaseController {    @Autowired    private IRoleService roleService;    /**     * 分页列表查询     * @param query     * @return     */    @GetMapping    public ResultVO<IPage<Role>> getByPage(RoleQuery query) {        IPage<Role> page1 = roleService.page(query);        ResultVO<IPage<Role>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);        return resultVO;    }    /**     * 角色新增     * @param role     * @return     */    @PostMapping    public ResultVO save(Role role) {        boolean isSuccess = roleService.save(role);        if(!isSuccess) {            log.warn("新增角色失败，执行返回结果：", isSuccess);            return unknownError();        }        return success();    }    /**     * 角色修改     * @param id     * @param role     * @return     */    @PutMapping("/{id:\\d+}")    public ResultVO update(@PathVariable Long id, Role role){        role.setId(id);        boolean isSuccess = roleService.updateById(role);        if(!isSuccess){            log.warn("修改用户失败，执行返回结果：", isSuccess);            return unknownError();        }        return success();    }    /**     * 角色删除     * @param id     * @return     */    @DeleteMapping("/{id:\\d+}")    public ResultVO delete(@PathVariable Long id){        Role role = new Role();        role.setStatus(StatusEnum.STOP.getValue());        role.setId(id);        boolean isSuccess = roleService.updateById(role);        if(!isSuccess){            log.warn("修改用户失败，执行返回结果：", isSuccess);            return unknownError();        }        return success();    }}