package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.dto.MenuDTO;
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
    List<MenuTreeVO> findMenuTreeVOByQuery(MenuQuery query);

    /**
     * 通过菜单ID集合获取菜单VO
     * @param ids
     * @return
     */
    List<MenuTreeVO> findMenuTreeVOByIds(List<Long> ids);

    /**
     * 通过ID查询菜单
     * @param id
     * @return
     */
    Menu findMenuById(Long id);

    /**
     * 根据主键ID查询当前菜单信息和父节点菜单名称
     * @param id
     * @return
     */
    MenuDTO findParentNameByPrimaryKey(Long id);

    /**
     * 根据菜单类型获取当前节点的上级节点集合
     * @param type
     * @return
     */
    List<Menu> findParentMenuByType(String type);

}
