package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.query.MenuQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.mapper.MenuMapper;
import com.aquaman.security.admin.service.IMenuService;
import com.aquaman.security.common.constant.AquamanConstant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<MenuTreeVO> getMenuByTree(MenuQuery query) {
        List<MenuTreeVO> list = recursionMenuTree(query);
        return list;
    }

    /**
     * 递归查询菜单树
     * @param query
     * @return
     */
    private List<MenuTreeVO> recursionMenuTree(MenuQuery query) {
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
                MenuTreeVO menuTreeVO = buildMenuTreeVO(menu);
                // MenuTreeVO增加到集合中
                menuTreeVOList.add(menuTreeVO);
                // 设置父菜单ID
                query.setParentId(menu.getId());
                // 递归查询下一级菜单,子节点集合
                menuTreeVO.setChildren(recursionMenuTree(query));

            }
            // 返回集合=》用于设置子节点
            return menuTreeVOList;
        }
        return null;
    }

    /**
     * Menu构建成MenuTreeVO
     * @param menu
     * @return
     */
    private MenuTreeVO buildMenuTreeVO(Menu menu) {
        if(menu != null){
            MenuTreeVO menuTreeVO = new MenuTreeVO();
            BeanUtils.copyProperties(menu, menuTreeVO);
            return menuTreeVO;
        } else {
            log.warn("{}入参Menu为空", AquamanConstant.LOG_TAG);
        }
        return null;
    }
}
