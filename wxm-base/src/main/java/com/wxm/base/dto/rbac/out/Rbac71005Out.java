package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.rbac.out.item.Item71005Out;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/21 14:30
 * @since 1.0.0
 */
@ApiModel("获取所有接口和已经授权的接口")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rbac71005Out implements java.io.Serializable{
    private long total;
    private java.util.List<Item71005Out>list=new ArrayList();
}
