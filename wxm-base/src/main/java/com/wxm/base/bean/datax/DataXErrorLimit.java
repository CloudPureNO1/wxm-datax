package com.wxm.base.bean.datax;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>
 *          *       errorLimit: { // 脏数据控制
 *      *         record: 0, // 对脏数据最大记录数阈值（record值）或者脏数据占比阈值（percentage值，当数量或百分比，DataX Job报错退出
 *      *         percentage: 0.02 // 者脏数据占比阈值（percentage值，当数量或百分比，DataX Job报错退出
 *      *       },
 *
 * </p>
 *
 * @author 王森明
 * @date 2022/7/8 9:32
 * @since 1.0.0
 */
@Data
public class DataXErrorLimit implements java.io.Serializable{
    private String  record;
    private String percentage;
    public Map<String,Object> buildErrorLimit(){
        Map<String,Object>map=new HashMap<>();
        if(StringUtils.hasLength(record)) {
            map.put("record", Long.valueOf(record.trim()));
        }
        if(StringUtils.hasLength(percentage)) {
            map.put("percentage", new BigDecimal(percentage.trim()));
        }
        return map;
    }
}
