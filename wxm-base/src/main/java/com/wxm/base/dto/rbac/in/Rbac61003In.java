package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BasePageDto;
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
public class Rbac61003In extends BasePageDto implements java.io.Serializable {
    private String dictId;
    private String dictLabel;
    private String dictUrl;
    private String creator;
    private String dictStatus;
    private String dictType;
}
