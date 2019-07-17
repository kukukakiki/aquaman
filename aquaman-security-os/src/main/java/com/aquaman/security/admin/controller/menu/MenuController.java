package com.aquaman.security.admin.controller.menu;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.dto.MenuDTO;
import com.aquaman.security.admin.entity.query.MenuQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.IMenuService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aquaman.security.admin.enums.MenuTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-04
 */
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 通过query对象查询菜单集合
     * @param query
     * @return
     */
    @GetMapping
    public ResultVO<List<MenuTreeVO>> get(MenuQuery query){
        // 通过query查询对象获取菜单集合，并且底层组装成'菜单树'结构
        List<MenuTreeVO> list = menuService.findMenuTreeVOByQuery(query);
        // 组装成功信息
        ResultVO<List<MenuTreeVO>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
    }

    /**
     * 根据ID查询菜单信息
     * step1,首先通过菜单ID获取菜单详情
     * step2,通过获取的菜单详情拿到当前菜单类{@link MenuTypeEnum}, 拿着菜单类型获取该类型的所有菜单集合
     * step3,获取某一个父节点，将菜单级别设置到{@link MenuDTO}
     * step4,将获取的菜单详情和该类型菜单集合设置到{@link MenuDTO}
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResultVO<MenuDTO> getById(@PathVariable Long id) {
        MenuDTO menuDTO = new MenuDTO();
        // step1
        Menu menu = menuService.findMenuById(id);
        // step2
        List<Menu> parentList = menuService.findParentMenuByType(menu.getType());
        if(CollectionUtils.isNotEmpty(parentList)) {
            // step3
            menuDTO.setParentLevel(
                    MenuTypeEnum.getLevelByKey(parentList.get(0).getType()));
        } else {
            menuDTO.setParentLevel(-1);
        }
        // step4
        menuDTO.setMenu(menu);
        menuDTO.setParentList(parentList);
        // 组装成功信息
        ResultVO<MenuDTO> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, menuDTO);
        return resultVO;
    }

    /**
     * 菜单枚举菜单编码和级别map
     * @return
     */
    @GetMapping("/keyLevelToMap")
    public ResultVO<Map<String, Integer>> keyLevelToMap() {
        Map<String, Integer> map = MenuTypeEnum.keyLevelToMap();
        ResultVO<Map<String, Integer>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, map);
        return resultVO;
    }

    /**
     * 保存
     * @param menu
     * @return
     */
    @PostMapping
    public ResultVO save(Menu menu) {
        boolean isSuccess = menuService.save(menu);
        if (!isSuccess) {
            return error(ResultCodeEnum.MENU_SAVE_ERROR);
        }
        return success();
    }

    /**
     * 修改
     * @param menu
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResultVO update(Menu menu) {
        boolean isSuccess = menuService.updateById(menu);
        if (!isSuccess) {
            return error(ResultCodeEnum.MENU_UPDATE_ERROR);
        }
        return success();
    }

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(StatusEnum.STOP.getValue());
        boolean isSuccess = menuService.updateById(menu);
        if(!isSuccess){
            return error(ResultCodeEnum.MENU_DELETE_ERROR);
        }
        return success();
    }
}
