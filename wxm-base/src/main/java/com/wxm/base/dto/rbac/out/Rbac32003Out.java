package com.wxm.base.dto.rbac.out;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>给角色添加权限</p>
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
public class Rbac32003Out implements java.io.Serializable{
    private String roleId;
    private List<String> list=new ArrayList<>();
}
