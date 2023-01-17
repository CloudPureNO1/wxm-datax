package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 * 资源表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Data
public class Rbac41001Out  implements java.io.Serializable {

    /**
     * 主键
     */
    private String  resourceId;

    /**
     * 资源类型：1节点 2 叶子 3 按钮
     */
    private String resourceType;

    /**
     * 资源唯一编码
     */
    private String resourceCode;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源地址url
     */
    private String resourceUrl;

    /**
     * 资源状态 0 无效 1有效
     */
    private String resourceStatus;

    /**
     * 资源排序
     */
    private Integer resourceNum;

    /**
     * 资源内部排序
     */
    private Integer orderNum;

    /**
     * 父节点Id
     */
    private String parentId;

    /**
     * 资源描述
     */
    private String resourceDesc;

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

    /**
     * 资源图标
     */
    private String resourceIcon;


}
