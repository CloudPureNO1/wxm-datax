package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/27 16:14
 * @since 1.0.0
 */
@Data
public class Rbac64002In extends BaseDto implements java.io.Serializable {
    private List<String>list=new ArrayList<>();
}
