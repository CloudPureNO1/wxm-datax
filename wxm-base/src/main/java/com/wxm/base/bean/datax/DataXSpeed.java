package com.wxm.base.bean.datax;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <p>
 * *       speed: { // 流量控制
 * *         channel: 3, // 控制同步时的最大并发数
 * *         byte: 1048576 // 控制传输速度，单位为byte/s，DataX运行会尽可能达到该速度但是不超过它
 * *       },
 *
 * </p>
 *
 * @author 王森明
 * @date 2022/7/8 9:30
 * @since 1.0.0
 */
@Data
public class DataXSpeed implements java.io.Serializable {
    private String channel;
    // byte 为java关键字,sByte 也是其他系统关键字
    private String spByte;

    /**
     * 如果使用getSpeed fastjson 会把get行数中的数值添加哦打json中
     * @return
     */
    public Map<String, Object> buildSpeed() {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.hasLength(channel)) {
            map.put("channel", Integer.valueOf(channel));
        }
        if (StringUtils.hasLength(spByte)) {
            map.put("byte", new BigDecimal(spByte));
        }
        return map;
    }
}
