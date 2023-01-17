package com.wxm.base.dto.rbac.out.item;

import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 14:32
 * @since 1.0.0
 */
@Data
public class Item71005Out implements java.io.Serializable{
    /**
     * Api 接口 ID
     */
    private String apiId;

    /**
     * 接口唯一编码
     */
    private String apiCode;

    /**
     * api 标题
     */
    private String apiTitle;

    /**
     * api 地址
     */
    private String apiUrl;

    /**
     * 1 有效 0 无效
     */
    private String apiStatus;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最近一次操作者
     */
    private String operator;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 接口描述
     */
    private String apiDesc;

    private String checked;
}
