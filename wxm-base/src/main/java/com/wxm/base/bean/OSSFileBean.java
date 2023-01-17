package com.wxm.base.bean;

import com.aliyun.oss.model.ObjectMetadata;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.InputStream;
import java.util.Date;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/10/21 14:57
 * @since 1.0.0
 */
@ApiModel("阿里云OSS 文件上传、下载对象")
@Data
public class OSSFileBean implements java.io.Serializable{
    @ApiModelProperty(value = "唯一值",notes="上传和下载的唯一值")
    private String key;

    @ApiModelProperty(value = "ObjectMetadata 对象",notes="OSS ObjectMetadata")
    private ObjectMetadata metadata = new ObjectMetadata();

    /** transient  不需要序列化 */
    @ApiModelProperty(value = "文件流",notes="上传和下载的文件流")
    private transient InputStream inputStream;
    @ApiModelProperty(value = "Content-Type")
    private String contentType;
    @ApiModelProperty(value = "文件大小")
    private long size;
    @ApiModelProperty(value = "上传时间",notes = "最近一次更新时间")
    private Date lastModified;
}
