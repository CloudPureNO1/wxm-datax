package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * api参数配置表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("rs_api_param")
public class RsApiParam {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * api_id::rs_api的id
     */
    @TableField("api_id")
    private String apiId;

    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 字段名
     */
    @TableField("param_name")
    private String paramName;

    /**
     * 字段中文名
     */
    @TableField("param_comment")
    private String paramComment;

    /**
     * 字段类型
     */
    @TableField("data_type")
    private String dataType;

    /**
     * 示例值
     */
    @TableField("demo")
    private String demo;

    /**
     * 排序号
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 参数类型：url/header/body/response
     */
    @TableField("param_type")
    private String paramType;

    /**
     * 表rs_api_sql的id(仅在sql接口和返回类型为response时才会生效)
     */
    @TableField("api_sql_id")
    private String apiSqlId;

    /**
     * 入参是否必填
     */
    @TableField("param_required")
    private String paramRequired;


}
