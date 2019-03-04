package com.aquaman.security.admin.user;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IUserService;
import com.aquaman.security.admin.util.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/1
 * @since 2019/3/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setAccount("test2009");
        user.setPassword("123456");
        user.setStatus(StatusEnum.START);
        userMapper.insert(user);
    }

    @Test
    public void update() {
        User updateUser = new User();
        updateUser.setId(31L);
        updateUser.setNickName("一剪梅");
        // updateUser.setPassword("1234561");
        userMapper.updateById(updateUser);
    }

    @Test
    public void findUserByPageTest(){
        System.out.println("----- baseMapper 自带分页 start ------");
        Page<User> page = new Page<User>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().eq("account", "test3"));
        Assert.assertSame(page, userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 end ------");

        System.out.println("----- 自定义 XML 分页 start ------");
        // PageQuery<User> myPage = new PageQuery<User>(1, 5);
        //UserQuery query = new UserQuery(1, 5);
        //query.setAccount("test5");
        //Page<User> userPage = userMapper.findUserByPage(query);
        // Assert.assertSame(myPage, userMyPage);
        //System.out.println("总条数 ------> " + userPage.getTotal());
        //System.out.println("当前页数 ------> " + userPage.getCurrent());
        //System.out.println("当前每页显示数 ------> " + userPage.getSize());
        //print(userPage.getRecords());
        System.out.println("----- 自定义 XML 分页 end ------");
    }

    @Test
    public void defaultPage() throws JsonProcessingException {

        UserQuery query =new UserQuery();
        query.setAccount("test3");

        IPage<User> page1 = userService.page(query);

        ResultVO<List<User>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1.getTotal());
        System.out.println(JSONUtil.objectToJSONString(resultVO));
    }

    private <T> void print(List<T> list) {
        /*if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }*/
    }
}
