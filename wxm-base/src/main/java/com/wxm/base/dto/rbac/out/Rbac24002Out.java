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
 * @date 2022/6/13 11:03
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rbac24002Out implements java.io.Serializable{
    /**
     * 主键
     */
    private String groupId;
    /**
     * 用户ID 列表
     */
    private List<String> list=new ArrayList<>();

}
