package com.wxm.base.dto.mall.out;


import com.wxm.base.dto.mall.out.item.Item11001Out;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mall11001Out implements java.io.Serializable {
    private List<Item11001Out>list=new ArrayList<>();
    private Long total;



}
