package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:16
 * @since 1.0.0
 */
@Data
public class Rbac32001In extends BaseDto implements java.io.Serializable {
    /**
     * 主键
     */
    private String roleId;

    /**
     * 角色唯一编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型：0 超级管理员角色，1 系统管理员角色，2 系统管理员角色，3 业务员角色，4 普通角色
     */
    private String roleType;

    /**
     * 0无效 1有效
     */
    private String roleStatus;

    /**
     * 描述
     */
    private String roleDesc;

    /**
     * 创建者
     */
    private String creator;

}
