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
 * 接口资源api
 * </p>
 *
 * @author 王森明
 * @since 2022-06-27
 */
@Getter
@Setter
@TableName("wxm_api")
public class WxmApi {

    /**
     * Api 接口 ID
     */
    @TableId(value = "api_id", type = IdType.AUTO)
    private Long apiId;

    /**
     * 接口唯一编码
     */
    @TableField("api_code")
    private String apiCode;

    /**
     * api 标题
     */
    @TableField("api_title")
    private String apiTitle;

    /**
     * api 地址
     */
    @TableField("api_url")
    private String apiUrl;

    /**
     * 1 有效 0 无效
     */
    @TableField("api_status")
    private String apiStatus;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最近一次操作者
     */
    @TableField("operator")
    private String operator;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 接口描述
     */
    @TableField("api_desc")
    private String apiDesc;


    @TableField(exist = false)
    private String checked;
}
