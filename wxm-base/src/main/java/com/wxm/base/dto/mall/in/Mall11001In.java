package com.wxm.base.dto.mall.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 9:06
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class Mall11001In extends BasePageDto implements java.io.Serializable{
    private String shopId;
    private String shopName;
    private String location;
    private String userId;
}
