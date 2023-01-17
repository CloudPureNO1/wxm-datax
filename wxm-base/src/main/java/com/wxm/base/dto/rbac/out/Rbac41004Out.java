package com.wxm.base.dto.rbac.out;

import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/16 11:31
 * @since 1.0.0
 */
@Data
public class Rbac41004Out implements java.io.Serializable{
    /**
     * 父节点Id
     */
    private String parentId;
    /**
     * 父节点名称
     */
    private String parentName;

    /**
     * 主键
     */
    private String  resourceId;

    /**
     * 资源类型：1节点 2 叶子
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
     * 资源描述
     */
    private String resourceDesc;
    /**
     * 资源图标
     */
    private String resourceIcon;
}
