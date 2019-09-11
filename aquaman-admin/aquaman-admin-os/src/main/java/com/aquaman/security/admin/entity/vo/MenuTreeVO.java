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
package com.aquaman.security.admin.entity.vo;

import com.aquaman.security.admin.enums.DisplayMenuEnum;
import com.aquaman.security.admin.enums.MenuTypeEnum;
import com.aquaman.security.admin.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树VO
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/4
 * @since 2019/3/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuTreeVO implements Cloneable {

    private Long id;

    /**
     * 所属系统id
     */
    private Long systemId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * api地址
     */
    private String url;

    /**
     * 前端路由地址
     */
    private String router;

    /**
     * 图标类型
     */
    private String iconType;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 0:隐藏,1:显示
     */
    private String display;

    /**
     * 状态
     */
    private String status;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号
     */
    private Long sort;

    /**
     * 权限Code
     */
    private String permissionCode;

    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModify;

    /**
     * 子集合
     */
    private List<MenuTreeVO> children;

    /**
     * 前端扩展属性，例：图标，名称
     */
    private MetaVO meta;

    /**
     * 该克隆方法采用深克隆
     * @return
     */
    @Override
    public MenuTreeVO clone() {
        MenuTreeVO vo = null;
        try {
            vo = (MenuTreeVO) super.clone();
            MetaVO metaVO = this.meta.clone();
            List<MenuTreeVO> list = new ArrayList<>();
            vo.setMeta(metaVO);
            vo.setChildren(list);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
