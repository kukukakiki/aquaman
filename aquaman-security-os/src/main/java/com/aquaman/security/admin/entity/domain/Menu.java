package com.aquaman.security.admin.entity.domain;

import com.aquaman.security.admin.enums.DisplayMenu;
import com.aquaman.security.admin.enums.MenuTypeEnum;
import com.aquaman.security.admin.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
    private MenuTypeEnum type;

    /**
     * 0:隐藏,1:显示
     */
    private DisplayMenu display;

    /**
     * 状态
     */
    private StatusEnum status;

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
     * 处理逻辑查阅{@link com.aquaman.security.admin.handler.mybatisplus.MyBatisPlusFillHandler}
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @TableField(update = "now()", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Long gmtModify;


}
