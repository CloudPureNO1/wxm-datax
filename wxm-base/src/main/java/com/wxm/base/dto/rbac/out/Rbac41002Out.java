package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item41002Out;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Data
public class Rbac41002Out implements java.io.Serializable {
    long total;
    List<Item41002Out>list=new ArrayList<>();



}
