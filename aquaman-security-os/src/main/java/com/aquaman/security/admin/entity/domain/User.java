package com.aquaman.security.admin.entity.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户实体类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
@Data
public class User implements UserDetails {

    /**
     * 主键ID
     */
    private Long id;


    /**
     * 用户名
     */
    private String account;


    /**
     * 密码
     */
    private String password;


    /**
     * 昵称
     */
    private String nickName;


    /**
     * 手机
     */
    private String mobile;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 所属部门ID
     */
    private Long deptId;


    /**
     * 状态
     */
    private String status;


    /**
     * 类型
     */
    private String type;


    /**
     * 最后登录时间
     */
    private Long loginTime;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}