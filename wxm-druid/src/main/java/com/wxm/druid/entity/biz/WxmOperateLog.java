package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author 王森明
 * @since 2024-06-13
 */
@Getter
@Setter
@TableName("wxm_operate_log")
public class WxmOperateLog {

    /**
     * 操作日志主键
     */
    @TableId(value = "log_id", type = IdType.INPUT)
    private String logId;

    /**
     * URL
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 方法
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 操作者ID
     */
    @TableField("operator_id")
    private String operatorId;

    /**
     * IP
     */
    @TableField("operator_ip")
    private String operatorIp;

    /**
     * 操作描述：比如 xxx模块：查询列表
     */
    @TableField("operator_info")
    private String operatorInfo;

    /**
     * 请求时间
     */
    @TableField("request_time")
    private LocalDateTime requestTime;

    /**
     * 响应时间
     */
    @TableField("response_time")
    private LocalDateTime responseTime;

    /**
     * 异常信息
     */
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * 异常事件
     */
    @TableField("exception_time")
    private LocalDateTime exceptionTime;


    /**
     * 资源主键
     */
    @TableField("resource_id")
    private String resourceId;


    /**
     * 资源地址url
     */
    @TableField("resource_url")
    private String resourceUrl;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

}
