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
package com.aquaman.security.admin.rolemenu;

import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.service.IMenuService;
import com.aquaman.security.admin.service.IRoleMenuService;
import com.aquaman.security.common.util.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/11
 * @since 2019/3/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleMenuTest {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Test
    public void getMenuTreeVO() throws JsonProcessingException {
        List<Long> list = new ArrayList<>();
        list.add(111L);
        list.add(1911L);
        List<String> result =  roleMenuService.findMenuIdsByRoleIds(list);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(objectMapper.writeValueAsString(result));
    }
}
