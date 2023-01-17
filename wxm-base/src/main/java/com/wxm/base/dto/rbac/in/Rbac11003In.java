package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BasePageDto;
import lombok.Data;

/**
 * <p>用户查询</p>
 * <p>根据条件</p>
 * <p>分页</p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@Data
public class Rbac11003In extends BasePageDto implements java.io.Serializable {
    private String userId;
    private String username;
    private String userType;
    private String userStatus;
    private String userRate;
    private String creator;
    private String groupId;
}
