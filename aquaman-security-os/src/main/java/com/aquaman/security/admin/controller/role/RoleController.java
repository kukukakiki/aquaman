package com.aquaman.security.admin.controller.role;import com.aquaman.security.admin.build.RoleMenuBuild;import com.aquaman.security.admin.controller.base.BaseController;import com.aquaman.security.admin.entity.domain.Role;import com.aquaman.security.admin.entity.domain.RoleMenu;import com.aquaman.security.admin.entity.dto.RoleMenuDTO;import com.aquaman.security.admin.entity.query.RoleQuery;import com.aquaman.security.admin.entity.vo.ResultVO;import com.aquaman.security.admin.enums.StatusEnum;import com.aquaman.security.admin.service.IRoleMenuService;import com.aquaman.security.admin.service.IRoleService;import com.aquaman.security.common.enums.ResultCodeEnum;import com.baomidou.mybatisplus.core.metadata.IPage;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.*;/** * 角色管理Rest * * @author wei wang * @since 2019-03-04 */@Slf4j@RestController@RequestMapping("/role")public class RoleController extends BaseController {    @Autowired    private IRoleService roleService;    @Autowired    private IRoleMenuService roleMenuService;    /**     * 分页列表查询     * @param query     * @return     */    @GetMapping    public ResultVO<IPage<Role>> getByPage(RoleQuery query) {        IPage<Role> page1 = roleService.page(query);        ResultVO<IPage<Role>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);        return resultVO;    }    /**     * 根据ID查询详情     * @param id     * @return     */    @GetMapping("/{id:\\d+}")    public ResultVO<RoleMenuDTO> getById(@PathVariable Long id) {        try {            RoleMenuDTO roleMenuDTO = roleService.queryRoleMenuIdsById(id);            ResultVO<RoleMenuDTO> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, roleMenuDTO);            return resultVO;        } catch (Exception e) {            return error(ResultCodeEnum.ROLE_GET_BY_ID_ERROR);        }    }    /**     * 角色新增     * @param roleMenuDTO     * @return     */    @PostMapping    public ResultVO save(RoleMenuDTO roleMenuDTO) {        try {            Role role = RoleMenuBuild.buildRoleByDTO(roleMenuDTO);            boolean isSuccess = roleService.save(role);            if (!isSuccess) {                log.warn("新增角色失败，执行返回结果：", isSuccess);                return error(ResultCodeEnum.ROLE_SAVE_ERROR);            } else {                roleMenuDTO.setId(role.getId());                RoleMenu roleMenu = RoleMenuBuild.buildRoleMenuByDTO(roleMenuDTO);                isSuccess = roleMenuService.save(roleMenu);                if (!isSuccess) {                    return error(ResultCodeEnum.ROLE_MENU_SAVE_ERROR);                }            }            return success();        } catch (Exception e) {            return  error(ResultCodeEnum.ROLE_SAVE_ERROR);        }    }    /**     * 角色修改     * @param id     * @param role     * @return     */    @PutMapping("/{id:\\d+}")    public ResultVO update(@PathVariable Long id, Role role){        role.setId(id);        boolean isSuccess = roleService.updateById(role);        if(!isSuccess){            log.warn("修改用户失败，执行返回结果：", isSuccess);            return unknownError();        }        return success();    }    /**     * 角色删除     * @param id     * @return     */    @DeleteMapping("/{id:\\d+}")    public ResultVO delete(@PathVariable Long id){        Role role = new Role();        role.setStatus(StatusEnum.STOP.getValue());        role.setId(id);        boolean isSuccess = roleService.updateById(role);        if(!isSuccess){            log.warn("修改用户失败，执行返回结果：", isSuccess);            return unknownError();        }        return success();    }}