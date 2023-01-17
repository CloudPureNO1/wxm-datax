package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BasePageDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>获取所有接口和已经授权的接口</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 14:29
 * @since 1.0.0
 */
@ApiModel("获取所有接口和已经授权的接口")
@Data
public class Rbac71005In extends BasePageDto implements java.io.Serializable {
    private String roleId;
    private String apiId;
    private String apiCode;
    private String apiTitle;
}
