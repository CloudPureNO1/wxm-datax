package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import lombok.Data;

/**
 * <p>角色查询</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@Data
public class Rbac31002In extends BasePageDto implements java.io.Serializable {
    private String roleId;
    private String roleName;
    private String roleCode;
    private String creator;
    private String roleType;
    private String roleStatus;
    private String groupId;
}
