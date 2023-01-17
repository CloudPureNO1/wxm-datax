package com.wxm.base.dto.rbac.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:16
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rbac32006Out implements java.io.Serializable {
    /**
     * 角色ID
     */
     private String roleId;
    /**
     * 接口ID列表
     */
    private List<String>list=new ArrayList<>();

}
