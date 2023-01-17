package com.wxm.base.dto.rbac.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>移除角色中的接口</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:04
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rbac34003Out implements java.io.Serializable{
    private String roleId;
    private List<String> list=new ArrayList<>();

}
