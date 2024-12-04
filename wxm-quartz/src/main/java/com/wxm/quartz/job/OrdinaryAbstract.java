package com.wxm.quartz.job;

import java.util.Map;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/11 14:17
 * @since 1.0.0
 */
public abstract class OrdinaryAbstract {
    /**
     * 定时任务执行方法
     */
    public abstract void execute(String params);
}
