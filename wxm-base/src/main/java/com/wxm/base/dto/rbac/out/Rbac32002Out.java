package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.BaseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 11:16
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rbac32002Out  implements java.io.Serializable {
    /**
     * 角色ID
     */
     private String roleId;
    /**
     * 资源ID列表
     */
    private List<String>list=new ArrayList<>();

}
