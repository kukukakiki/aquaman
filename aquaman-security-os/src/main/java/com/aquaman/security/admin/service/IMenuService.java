package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.query.MenuQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单服务
 * @author kukukakiki
 * @since 2019-03-04
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单树
     * @param query
     * @return
     */
    List<MenuTreeVO> findMMenuTreeVOByQuery(MenuQuery query);

    /**
     * 通过菜单ID集合获取菜单VO
     * @param ids
     * @return
     */
    List<MenuTreeVO> findMenuTreeVOByIds(List<Long> ids);

}
