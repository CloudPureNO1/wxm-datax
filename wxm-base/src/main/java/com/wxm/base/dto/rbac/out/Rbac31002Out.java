package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item31002Out;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Data
public class Rbac31002Out implements java.io.Serializable {
    long total;
    List<Item31002Out>list=new ArrayList<>();

}
