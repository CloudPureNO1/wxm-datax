package com.wxm.base.dto.rbac.in;

import com.wxm.base.dto.BaseDto;
import com.wxm.base.dto.BasePageDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>字典查询</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 11:55
 * @since 1.0.0
 */
@Data
public class Rbac61002In extends BasePageDto implements java.io.Serializable {
    List<String>typeList=new ArrayList<>();
}