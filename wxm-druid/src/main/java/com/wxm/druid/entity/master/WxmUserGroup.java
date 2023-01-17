package com.wxm.druid.entity.master;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户-用户组关联表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-17
 */
@Getter
@Setter
@TableName("wxm_user_group")
public class WxmUserGroup {

    /**
     * 主键
     */
    @TableId(value = "user_group_id", type = IdType.AUTO)
    private Long userGroupId;

    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户组主键
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
