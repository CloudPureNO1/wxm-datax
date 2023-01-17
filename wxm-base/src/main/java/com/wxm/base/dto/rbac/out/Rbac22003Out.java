package com.wxm.base.dto.rbac.out;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:03
 * @since 1.0.0
 */
@Data
public class Rbac22003Out implements java.io.Serializable{
    /**
     * 主键
     */
    private String groupId;
    /**
     * 角色ID 列表
     */
    private List<String>list=new ArrayList<>();

}
