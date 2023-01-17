package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>给角色添加接口</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:02
 * @since 1.0.0
 */
@Data
public class Rbac32004In extends BaseDto implements java.io.Serializable {
    private String roleId;
    /**接口列表*/
    private List<String>list=new ArrayList<>();
}
