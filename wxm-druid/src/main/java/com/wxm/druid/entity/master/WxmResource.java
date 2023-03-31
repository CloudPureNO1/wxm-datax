package com.wxm.druid.entity.master;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Getter
@Setter
@TableName("wxm_resource")
public class WxmResource {

    /**
     * 主键
     */
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    /**
     * 资源类型：1节点 2 叶子 3 按钮
     */
    @TableField("resource_type")
    private String resourceType;

    /**
     * 资源唯一编码
     */
    @TableField("resource_code")
    private String resourceCode;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 资源名称（英文）国际化使用
     */
    @TableField("resource_name_en")
    private String resourceNameEn;

    /**
     * 资源地址url
     */
    @TableField("resource_url")
    private String resourceUrl;

    /**
     * 资源状态 0 无效 1有效
     */
    @TableField("resource_status")
    private String resourceStatus;

    /**
     * 资源排序
     */
    @TableField("resource_num")
    private Integer resourceNum;

    /**
     * 资源内部排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 父节点Id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 资源描述
     */
    @TableField("resource_desc")
    private String resourceDesc;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;
    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 资源图标
     */
    @TableField("resource_icon")
    private String resourceIcon;


}
