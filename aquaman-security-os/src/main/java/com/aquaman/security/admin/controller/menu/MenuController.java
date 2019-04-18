package com.aquaman.security.admin.controller.menu;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.dto.MenuDTO;
import com.aquaman.security.admin.entity.query.MenuQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.IMenuService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

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

    @GetMapping
    public ResultVO<List<MenuTreeVO>> get(MenuQuery query){
        List<MenuTreeVO> list = menuService.findMenuTreeVOByQuery(query);
        ResultVO<List<MenuTreeVO>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
    }

    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResultVO<MenuDTO> getById(@PathVariable Long id) {
        try {
        MenuDTO menuDTO = new MenuDTO();
        Menu menu = menuService.findMenuById(id);
        List<Menu> parentList = menuService.findParentMenuByType(menu.getType());
        menuDTO.setMenu(menu);
        menuDTO.setParentList(parentList);
        ResultVO<MenuDTO> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, menuDTO);
        return resultVO;
        } catch (Exception e) {
            log.error("根据ID：{}查询菜单信息：{}", id, e.getMessage());
        }
        return unknownError();
    }

    @PostMapping
    public ResultVO save(Menu menu) {
        boolean isSuccess = menuService.save(menu);
        if(!isSuccess){
            return unknownError();
        }
        return success();
    }

    @PutMapping("/{id:\\d+}")
    public ResultVO update(@PathVariable Long id, Menu menu) {
        menu.setId(id);
        boolean isSuccess = menuService.updateById(menu);
        if(!isSuccess){
            return unknownError();
        }
        return success();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(StatusEnum.STOP.getValue());
        boolean isSuccess = menuService.updateById(menu);
        if(!isSuccess){
            return unknownError();
        }
        return success();
    }
}
