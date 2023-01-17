package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item21002Out;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;


/**
 * <p>
 * 用户组
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rbac21002Out implements java.io.Serializable {
    private Long total;
    private List<Item21002Out> list=new ArrayList<>();;


}
