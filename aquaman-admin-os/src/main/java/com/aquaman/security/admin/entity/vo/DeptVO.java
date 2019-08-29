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

import lombok.Data;

import java.util.List;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-05-08
 * @since 2019-05-08
 */
@Data
public class DeptVO {

    private Long id;

    private String name;

    /**
     * 类型（集团、子公司、部门、小组）
     */
    private String type;

    private String status;

    /**
     * 部门地址
     */
    private String address;

    private String remark;

    private Long sort;

    private Long parentId;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;

    private List<DeptVO> children;
}
