package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.build.MenuTreeBuild;
import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.dto.MenuDTO;
import com.aquaman.security.admin.entity.query.MenuQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.enums.MenuTypeEnum;
import com.aquaman.security.admin.mapper.MenuMapper;
import com.aquaman.security.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-04
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuTreeVO> findMenuTreeVOByQuery(MenuQuery query) {
        List<MenuTreeVO> list = recursionMenuTreeByMenuQuery(query);
        return list;
    }

    @Override
    public List<MenuTreeVO> findMenuTreeVOByIds(List<Long> ids) {
        MenuQuery query = new MenuQuery();
        // 通过id集合获取菜单集合
        List<Menu> list = menuMapper.selectList(query.instanceQueryWrapper().in("id", ids));
        if(CollectionUtils.isNotEmpty(list)){
            List<MenuTreeVO> menuTreeVOList = recursionMenuTreeByMenuAllAndParentId(list, -1L);
            return menuTreeVOList;
        }
        return null;
    }

    @Override
    public Menu findMenuById(Long id) {
        Assert.notNull(id, "ID不能为空");
        return menuMapper.selectById(id);
    }

    @Override
    public MenuDTO findParentNameByPrimaryKey(Long id) {
        return menuMapper.selectParentNameByPrimaryKey(id);
    }

    @Override
    public List<Menu> findParentMenuByType(String type) {
        int menuLevel = MenuTypeEnum.getLevelByKey(type);
        String menuType = MenuTypeEnum.getKeyByLevel(menuLevel - 1);
        if(StringUtils.isNotEmpty(menuType)) {
            MenuQuery menuQuery = new MenuQuery();
            menuQuery.setType(menuType);
            return menuMapper.selectList(menuQuery.instanceQueryWrapper());
        }
        return null;
    }

    @Override
    public MenuDTO findMenuDTOByPrimaryKey(Long id) {
        MenuDTO menuDTO = new MenuDTO();
        // step1
        Menu menu = this.findMenuById(id);
        // step2
        List<Menu> parentList = this.findParentMenuByType(menu.getType());
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
        return menuDTO;
    }

    /**
     * 递归菜单树VO-菜单集合和父节点
     * @param menuAll 所有菜单集合
     * @param parentId 需要遍历的父节点ID
     * @return
     */
    private List<MenuTreeVO> recursionMenuTreeByMenuAllAndParentId(List<Menu> menuAll, Long parentId) {
        if(CollectionUtils.isNotEmpty(menuAll)) {
            // 菜单VO集合
            List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
            for(Menu menu : menuAll) {
                // 如果当前菜单为根结点
                if(menu.getParentId().equals(parentId)) {
                    // 将Menu构建成MenuTreeVO对象
                    MenuTreeVO menuTreeVO = MenuTreeBuild.buildMenuTreeVO(menu);
                    // MenuTreeVO增加到集合中
                    menuTreeVOList.add(menuTreeVO);
                    // 递归该节点
                    menuTreeVO.setChildren(recursionMenuTreeByMenuAllAndParentId(menuAll, menu.getId()));
                }
            }
            return menuTreeVOList;
        }
        return null;
    }

    /**
     * 递归查询菜单树-根据菜单Query对象查询菜单树
     * @param query
     * @return
     */
    private List<MenuTreeVO> recursionMenuTreeByMenuQuery(MenuQuery query) {
        // 如果parentId（父节点ID）为空则默认查询-1节点
        if(query == null) {
            query = new MenuQuery();
            query.setParentId(-1L);
        }
        // 获取查询菜单集合
        List<Menu> parentMenuList = menuMapper.selectList(query.instanceQueryWrapper());
        if(CollectionUtils.isNotEmpty(parentMenuList)){
            // 菜单VO集合
            List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
            for(Menu menu : parentMenuList) {
                // 将Menu构建成MenuTreeVO对象
                MenuTreeVO menuTreeVO = MenuTreeBuild.buildMenuTreeVO(menu);
                // MenuTreeVO增加到集合中
                menuTreeVOList.add(menuTreeVO);
                // 设置父菜单ID
                query.setParentId(menu.getId());
                // 递归查询下一级菜单,子节点集合
                menuTreeVO.setChildren(recursionMenuTreeByMenuQuery(query));

            }
            // 返回集合=》用于设置子节点
            return menuTreeVOList;
        }
        return null;
    }
}
