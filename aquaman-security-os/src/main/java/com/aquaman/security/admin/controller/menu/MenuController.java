package com.aquaman.security.admin.controller.menu;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.domain.User;
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
        List<MenuTreeVO> list = menuService.findMMenuTreeVOByQuery(query);
        ResultVO<List<MenuTreeVO>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
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
        menu.setStatus(StatusEnum.STOP);
        boolean isSuccess = menuService.updateById(menu);
        if(!isSuccess){
            return unknownError();
        }
        return success();
    }
}
