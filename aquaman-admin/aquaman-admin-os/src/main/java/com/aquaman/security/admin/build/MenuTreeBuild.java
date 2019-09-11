/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.admin.build;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.entity.vo.MetaVO;
import com.aquaman.security.common.constant.AquamanConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-05-05
 * @since 2019-05-05
 */
@Slf4j
public class MenuTreeBuild {


    /**
     * Menu构建成MenuTreeVO
     * @param menu
     * @return
     */
    public static MenuTreeVO buildMenuTreeVO(Menu menu) {
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        // 构建扩展信息
        MetaVO meta = new MetaVO();
        return buildMenuTreeVO(menu, menuTreeVO, meta);
    }

    /**
     * Menu构建成MenuTreeVO
     * @param menu
     * @return
     */
    public static MenuTreeVO buildMenuTreeVO(Menu menu, MenuTreeVO menuTreeVO, MetaVO meta) {
        Assert.notNull(menu, "菜单关系DTO对象不能为空");
        BeanUtils.copyProperties(menu, menuTreeVO);
        // 构建扩展信息
        meta.setTitle(menu.getName());
        meta.setIncon(menu.getIconType());
        meta.setType(menu.getType());
        menuTreeVO.setMeta(meta);
        return menuTreeVO;
    }



    /**
     * Menu构建成MenuTreeVO
     * @param menu
     * @return
     */
    public static MenuTreeVO buildMenuTreeVO(Menu menu, MenuTreeVO menuTreeVO) {
        Assert.notNull(menu, "菜单关系DTO对象不能为空");
        BeanUtils.copyProperties(menu, menuTreeVO);
        // 构建扩展信息
        menuTreeVO.getMeta().setIncon(menu.getIconType()).setTitle(menu.getName()).setType(menu.getType());
        return menuTreeVO;
    }
}
