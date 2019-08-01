package com.aquaman.security.admin.entity.domain;

import com.aquaman.security.admin.validator.annotation.AccountNotRepeat;
import com.aquaman.security.common.view.BaseView;
import com.aquaman.security.common.view.DetailView;
import com.aquaman.security.common.view.ListView;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 
 * </p>
 * @author kukukakiki
 * @since 2019-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_user")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    // TODO @AccountNotRepeat 由于更新也会校验重复,导致更新失败
    @JsonView(BaseView.class)
    private String account;

    /**
     * 用户姓名
     */
    @JsonView(BaseView.class)
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    @JsonView(BaseView.class)
    private String nickName;

    /**
     * 手机
     */
    @JsonView(BaseView.class)
    private String mobile;

    /**
     * 邮箱
     */
    @JsonView(DetailView.class)
    private String email;

    /**
     * 部门ID
     */
    @JsonView(DetailView.class)
    private Long deptId;

    /**
     * 状态
     */
    @JsonView(DetailView.class)
    private String status;

    /**
     * 会员类型
     */
    @JsonView(DetailView.class)
    private String type;

    /**
     * 登录时间
     */
    @JsonView(ListView.class)
    private Long loginTime;

    /**
     * 图像对应ad_upload_file表主键ID
     */
    private Long imageFileId;

    /**
     * 权限Code
     */
    private String permissionCode;

    /**
     * 处理逻辑查阅{@link com.aquaman.security.admin.handler.mybatisplus.MyBatisPlusFillHandler}
     * 创建时间
     */
    @JsonView(ListView.class)
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @JsonView(ListView.class)
    @TableField(update = "now()", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Long gmtModify;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO AuthorityUtils.commaSeparatedStringToAuthorityList("admin");无法获取到值,目前先屏蔽掉
        return null;
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
