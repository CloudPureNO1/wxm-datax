package com.wxm.base.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/6 15:21
 * @since 1.0.0
 */
@Accessors(chain = true)
@Data
public class JobBean {
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String jobDescription;
    private String triggerDescription;
    private String cron;
}
