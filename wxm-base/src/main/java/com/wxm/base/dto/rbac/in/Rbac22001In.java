package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:00
 * @since 1.0.0
 */
@Data
public class Rbac22001In extends BaseDto implements java.io.Serializable {
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

}
