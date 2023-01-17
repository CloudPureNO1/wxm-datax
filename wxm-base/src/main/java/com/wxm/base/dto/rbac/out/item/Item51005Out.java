package com.wxm.base.dto.rbac.out.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 10:38
 * @since 1.0.0
 */
@ApiModel("权限信息")
@Data
public class Item51005Out implements java.io.Serializable{
    /**
     * 是否授权 1是 0 否
     */
    @ApiModelProperty("是否授权 1是 0 否")
    private String checked;
    /**
     * 主键
     */
    private String permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;
    private String permissionStatus;
    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 描述
     */
    private String permissionDesc;

    /**
     * 创建者
     */
    private String creator;
    /**
     * 操作者
     */
    private String operator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


    private String permissionType;
}
