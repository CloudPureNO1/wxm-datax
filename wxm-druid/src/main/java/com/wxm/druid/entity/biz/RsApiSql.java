package com.wxm.druid.entity.biz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 接口sql配置表
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("rs_api_sql")
public class RsApiSql {

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
     * 返回时对象名称
     */
    @TableField("result_code")
    private String resultCode;

    /**
     * 名称
     */
    @TableField("result_name")
    private String resultName;

    /**
     * 所属数据源（默认为数仓）
     */
    @TableField("datasource_id")
    private String datasourceId;

    /**
     * sql语句
     */
    @TableField("query_sql")
    private String querySql;

    /**
     * 返回结果包含null值
     */
    @TableField("contains_null")
    private String containsNull;

    /**
     * 返回类型（对象或者数组）
     */
    @TableField("result_type")
    private String resultType;

    /**
     * 排序号
     */
    @TableField("seq")
    private Integer seq;


}
