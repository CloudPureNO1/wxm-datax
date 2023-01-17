package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/10/27 16:14
 * @since 1.0.0
 */
@Data
public class Rbac63001In extends BaseDto implements java.io.Serializable {
    private String dictId;
    private String dictType;
    private String dictValue;
    private String dictLabel;
    private String dictStatus;
    private String dictParentValue;
    private String orderNum;
}
