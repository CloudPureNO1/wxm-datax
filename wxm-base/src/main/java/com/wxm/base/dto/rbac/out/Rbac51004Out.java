package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item51003Out;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/17 10:04
 * @since 1.0.0
 */
@Accessors(chain = true)
@Data
public class Rbac51004Out implements java.io.Serializable{
    private List<String>list=new ArrayList<>();
}
