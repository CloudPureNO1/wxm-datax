package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>编辑/新增 角色的资源</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:16
 * @since 1.0.0
 */
@Data
public class Rbac32002In extends BaseDto implements java.io.Serializable {
    /**
     * 角色ID
     */
     private String roleId;
    /**
     * 资源ID列表
     */
    private List<String>list=new ArrayList<>();

}
