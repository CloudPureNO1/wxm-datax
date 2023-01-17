package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item11002Out;
import com.wxm.base.dto.rbac.out.item.Item11003Out;
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
public class Rbac11003Out implements java.io.Serializable {
    private Long total;
    private List<Item11003Out>list=new ArrayList<>();;



}
