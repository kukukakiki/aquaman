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
package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.Menu;
import com.aquaman.security.admin.enums.DisplayMenuEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/4
 * @since 2019/3/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuQuery extends PageQuery<Menu> implements Serializable {

    private static final long serialVersionUID = 2529460702876533867L;

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
     * 类型(group:菜单组,menu:菜单,button:按钮,page:页面,)
     */
    private String type;

    /**
     * 0:隐藏,1:显示
     */
    private DisplayMenuEnum display;

    /**
     * 状态
     */
    private String status;

    /**
     * 父ID
     */
    private Long parentId;

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

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModify;

    /**
     * 是否删除(0:否;1:是)
     */
    private Integer isDeleted;
}
