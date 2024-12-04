package com.wxm.druid.entity.quartz;

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
 * datax 定时任务文件信息
 * </p>
 *
 * @author 王森名
 * @since 2022-07-06
 */
@Getter
@Setter
@TableName("data_x_details")
public class DataXDetails {

    /**
     * 主键
     */
    @TableId(value = "file_id", type = IdType.INPUT)
    private String fileId;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 1 有效 0 无效
     */
    @TableField("file_status")
    private String fileStatus;

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
    @TableField("file_desc")
    private String fileDesc;


}
