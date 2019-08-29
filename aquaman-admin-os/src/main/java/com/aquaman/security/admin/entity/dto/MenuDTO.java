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
package com.aquaman.security.admin.entity.dto;

import com.aquaman.security.admin.entity.domain.Menu;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 菜单DTO
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/4/13
 * @since 2019/4/13
 */
@Data
public class MenuDTO {

    /**
     * 菜单实体类
     */
    private Menu menu;

    /**
     * 父节点集合
     */
    private List<Menu> parentList;

    /**
     * 菜单级别map
     */
    private Map<String, Integer> menuLevelMap;

    /**
     * 父节点级别
     */
    private Integer parentLevel;
}
