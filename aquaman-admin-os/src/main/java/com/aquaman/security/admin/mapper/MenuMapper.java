package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.dto.MenuDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据主键ID查询当前菜单信息和父节点菜单名称
     * @param id
     * @return
     */
    MenuDTO selectParentNameByPrimaryKey(Long id);
}
