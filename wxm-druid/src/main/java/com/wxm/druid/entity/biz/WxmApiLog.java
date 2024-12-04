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
 * 接口日志
 * </p>
 *
 * @author 王森明
 * @since 2024-06-13
 */
@Getter
@Setter
@TableName("wxm_api_log")
public class WxmApiLog {

    /**
     * 日志ID
     */
    @TableId(value = "log_id", type = IdType.INPUT)
    private String logId;

    /**
     * 接口地址
     */
    @TableField("trans_url")
    private String transUrl;

    /**
     * 交易编码
     */
    @TableField("trans_code")
    private String transCode;

    /**
     * 交易名称
     */
    @TableField("trans_name")
    private String transName;

    /**
     * 入参
     */
    @TableField("data_in")
    private String dataIn;

    /**
     * 出参
     */
    @TableField("datao_out")
    private String dataoOut;

    /**
     * 请求时间
     */
    @TableField("request_time")
    private LocalDateTime requestTime;

    /**
     * 相应时间
     */
    @TableField("response_time")
    private LocalDateTime responseTime;

    /**
     * 异常时间
     */
    @TableField("exception_time")
    private LocalDateTime exceptionTime;

    /**
     * 异常信息
     */
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * 调用者信息json，预留
     */
    @TableField("caller")
    private String caller;

    /**
     * 接口类型
     */
    @TableField("api_type")
    private String apiType;

    /**
     * 状态：1 请求 2 相应 3 异常
     */
    @TableField("state")
    private String state;

    /**
     * IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 类名
     */
    @TableField("class_name")
    private String className;

    /**
     * 方法名
     */
    @TableField("method_name")
    private String methodName;

    /**
     * 执行时长
     */
    @TableField("execution_time")
    private String executionTime;

    /**
     * 操作者ID
     */
    @TableField("operator_id")
    private String operatorId;
}
