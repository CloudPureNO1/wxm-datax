package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item71002Out;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rbac71002Out implements java.io.Serializable {
    private Long total;
    private List<Item71002Out>list=new ArrayList<>();;



}
