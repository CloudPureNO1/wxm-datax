package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BasePageDto;
import lombok.Data;

/**
 * <p>接口查询</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@Data
public class Rbac71003In extends BasePageDto implements java.io.Serializable {
    private String roleId;
    private String apiId;
    private String apiTitle;
    private String apiCode;
    private String apiUrl;
    private String creator;
    private String apiStatus;
}
