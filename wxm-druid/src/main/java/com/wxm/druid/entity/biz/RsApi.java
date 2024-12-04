package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * api表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("rs_api")
public class RsApi {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 所属项目
     */
    @TableField("project_id")
    private String projectId;

    /**
     * 接口名称
     */
    @TableField("api_name")
    private String apiName;

    /**
     * 接口代码
     */
    @TableField("api_code")
    private String apiCode;

    /**
     * 代理地址
     */
    @TableField("agent_url")
    private String agentUrl;

    /**
     * 接口描述
     */
    @TableField("api_desc")
    private String apiDesc;

    /**
     * 接口类型::代理接口、单表接口、sql接口
     */
    @TableField("api_type")
    private String apiType;

    /**
     * 连通监测
     */
    @TableField("connect_monitor")
    private String connectMonitor;

    /**
     * 连通状态
     */
    @TableField("connect_status")
    private String connectStatus;

    /**
     * 接口模式::http(s)，ws
     */
    @TableField("api_protocol")
    private String apiProtocol;

    /**
     * 请求方式::GET，POST
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求类型::form，json，text
     */
    @TableField("request_content_type")
    private String requestContentType;

    /**
     * 请求示例
     */
    @TableField("request_demo")
    private String requestDemo;

    /**
     * 响应示例
     */
    @TableField("response_demo")
    private String responseDemo;

    /**
     * 表资产id
     */
    @TableField("table_id")
    private String tableId;

    /**
     * sql语句
     */
    @TableField("query_sql")
    private String querySql;

    /**
     * 所属数据源（默认为数仓）
     */
    @TableField("datasource_id")
    private String datasourceId;

    /**
     * 是否资产
     */
    @TableField("asset")
    private String asset;

    /**
     * 有效标识
     */
    @TableField("valid")
    private String valid;

    /**
     * 创建人
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * api返回结果是否加密
     */
    @TableField("api_encrypted")
    private String apiEncrypted;

    /**
     * 级联系统行政区划
     */
    @TableField("link_area_code")
    private String linkAreaCode;

    /**
     * api所属行政区划
     */
    @TableField("area_code")
    private String areaCode;


}
