package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>移除角色中的权限</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:02
 * @since 1.0.0
 */
@Data
public class Rbac34002In extends BaseDto implements java.io.Serializable {
    private String roleId;
    private List<String> list=new ArrayList<>();

}
