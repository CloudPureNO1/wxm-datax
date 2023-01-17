package com.wxm.base.bean;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 异常信息类
 * @Author 王森明
 */
@ApiModel(value="异常的信息")
@Data
public class ExceptionData {
    @ApiModelProperty(value="异常信息",name="exceptionMsg",notes="异常信息")
    private String exceptionMsg;
    @ApiModelProperty(value="异常类",name="className",notes = "发生异常的类的名称")
    private String className;
    @ApiModelProperty(value="异常方法",name="methodName",notes = "发生异常的方法的名称")
    private String methodName;
    @ApiModelProperty(value="异常行号",name="lineNumber",notes = "发生异常的行号")
    private Integer lineNumber;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
