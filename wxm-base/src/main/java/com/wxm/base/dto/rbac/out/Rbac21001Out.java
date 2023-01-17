package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Data
public class Rbac21001Out   implements java.io.Serializable {

    /**
     * 主键
     */
    private String groupId;

    /**
     * 用户组唯一编码
     */
    private String groupCode;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组类型：0 超级管理员用户组，1 系统管理员用户组，2 系统管理员用户组，3 业务员用户组，4 普通用户组
     */
    private String groupType;

    /**
     * 0无效 1有效
     */
    private String groupStatus;

    /**
     * 描述
     */
    private String groupDesc;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
