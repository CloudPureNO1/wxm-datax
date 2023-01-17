package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item31003Out;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@Data
public class Rbac31004Out implements java.io.Serializable {
    List<String>list=new ArrayList<>();
}
