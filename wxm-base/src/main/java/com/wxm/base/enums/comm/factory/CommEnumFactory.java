package com.wxm.base.enums.comm.factory;

import com.wxm.base.enums.comm.ICommEnum;
import com.wxm.base.enums.comm.QuartzEnum;
import com.wxm.base.enums.comm.RbacEnum;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-17 18:21:25
 */
public class CommEnumFactory {
    // 工厂方法，根据类型字符串返回对应的枚举实例
    public static ICommEnum getTypeEnum(String type,String code) {
        switch (type) {
            case "rbac":
                // 根据实际情况选择返回哪个实例
                return RbacEnum.getInstance(code);
            case "quartz":
                return QuartzEnum.getInstance(code);
            default:
                return null;
        }
    }
}
