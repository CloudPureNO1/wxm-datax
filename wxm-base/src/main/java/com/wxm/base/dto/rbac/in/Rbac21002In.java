package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import lombok.Data;

/**
 * <p>用户组查询</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@Data
public class Rbac21002In extends BasePageDto implements java.io.Serializable {
    private String groupId;
    private String groupName;
    private String groupCode;
    private String groupType;
    private String groupStatus;
    private String creator;
}
