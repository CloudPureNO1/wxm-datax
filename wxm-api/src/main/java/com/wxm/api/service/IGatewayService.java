package com.wxm.api.service;

import com.wxm.base.bean.JobBean;
import com.wxm.base.exception.BaseException;

import java.io.IOException;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/5 17:27
 * @since 1.0.0
 */
public interface IGatewayService {
    /**
     * 新增定时任务
     * @param text  定时任务内容（json格式）
     * @return
     * @throws BaseException
     * @throws IOException
     */
    String addJob(String text) throws BaseException, IOException;

    /**
     * 新增定时任务
     * @param jobBean
     * @param text  定时任务内容（json格式）
     * @return
     * @throws BaseException
     * @throws IOException
     */
    String addJob(JobBean jobBean,String text) throws BaseException, IOException;
}
