package com.wxm.druid.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.BlobTypeHandler;

import java.sql.Blob;

/**
 * <p>
 * qrtz_job_details表 用来存储已配置的Job的详细信息。
 * </p>
 *
 * @author 王森明
 * @since 2022-04-28
 */
@Accessors(chain = true)
@Getter
@Setter
@TableName("QRTZ_JOB_DETAILS")
public class JobDetails {

    /**
     * 调度名称
     */
    @TableField(value = "SCHED_NAME")
    @MppMultiId
    private String schedName;

    /**
     * 集群中job的名称
     */
    @TableField(value = "JOB_NAME")
    @MppMultiId
    private String jobName;

    /**
     * 集群中job的所属组的名称
     */
    @TableField(value = "JOB_GROUP")
    @MppMultiId
    private String jobGroup;

    /**
     * 详细描述信息
     */
    @TableField("`DESCRIPTION`")
    private String description;

    /**
     * 集群中job实现类的全名，quartz就是根据这个路径到classpath找到该job类
     */
    @TableField("JOB_CLASS_NAME")
    private String jobClassName;

    /**
     * 是否持久化，把该属性设置为1，quartz会把job持久化到数据库中
     */
    @TableField("IS_DURABLE")
    private String isDurable;

    /**
     * 是否并发执行
     */
    @TableField("IS_NONCONCURRENT")
    private String isNonconcurrent;

    /**
     * 是否更新数据
     */
    @TableField("IS_UPDATE_DATA")
    private String isUpdateData;

    /**
     * 是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则该job会被重新执行
     */
    @TableField("REQUESTS_RECOVERY")
    private String requestsRecovery;

    /**
     * 一个blob字段，存放持久化job对象,
     * 采用 "org.apache.ibatis.type.BlobTypeHandler"  来处理blob
     */
    @TableField(value="JOB_DATA",typeHandler = BlobTypeHandler.class)
    private Blob jobData;


}
