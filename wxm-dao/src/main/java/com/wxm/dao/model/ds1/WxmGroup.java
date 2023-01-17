package com.wxm.dao.model.ds1;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Getter
@Setter
@TableName("wxm_group")
public class WxmGroup {

    /**
     * 主键
     */
    @TableId(value = "group_id", type = IdType.AUTO)
    private Long groupId;

    /**
     * 用户组唯一编码
     */
    @TableField("group_code")
    private String groupCode;

    /**
     * 用户组名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 用户组类型：0 超级管理员用户组，1 系统管理员用户组，2 系统管理员用户组，3 业务员用户组，4 普通用户组
     */
    @TableField("group_type")
    private String groupType;

    /**
     * 0无效 1有效
     */
    @TableField("group_status")
    private String groupStatus;

    /**
     * 描述
     */
    @TableField("group_desc")
    private String groupDesc;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
