package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:02
 * @since 1.0.0
 */
@Data
public class Rbac51002In extends BasePageDto implements java.io.Serializable {
    private String permissionId;
    private String permissionName;
    private String creator;
    private String resourceId;
    private String roleId;
    private String permissionCode;
    private String permissionType;
    private String permissionStatus;
}
