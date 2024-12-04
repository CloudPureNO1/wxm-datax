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
 * 
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("rs_api_server_log")
public class RsApiServerLog {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 请求应用id
     */
    @TableField("client_id")
    private String clientId;

    @TableField("project_id")
    private String projectId;

    /**
     * 接口编码
     */
    @TableField("api_code")
    private String apiCode;

    /**
     * 接口id
     */
    @TableField("api_id")
    private String apiId;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 花费时间
     */
    @TableField("time_cost")
    private Integer timeCost;

    /**
     * 日志8位日期
     */
    @TableField("log_date")
    private Long logDate;

    /**
     * 数据总量
     */
    @TableField("data_count")
    private Long dataCount;

    /**
     * 请求参数
     */
    @TableField("request_param")
    private String requestParam;

    /**
     * 请求体
     */
    @TableField("request_body")
    private String requestBody;

    /**
     * 返回体
     */
    @TableField("response_body")
    private String responseBody;

    /**
     * 请求成功或者失败
     */
    @TableField("result_state")
    private String resultState;

    /**
     * 错误信息
     */
    @TableField("error_msg")
    private String errorMsg;

    /**
     * 请求ip地址
     */
    @TableField("request_ip")
    private String requestIp;


}
