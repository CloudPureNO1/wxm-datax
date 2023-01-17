package com.wxm.datax.utils;

import com.alibaba.datax.common.exception.ExceptionTracker;
import com.alibaba.datax.core.Engine;
import com.wxm.datax.config.DataXPropertiesConfig;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/26 12:18
 * @since 1.0.0
 */
@Component
@Slf4j
public class DataXUtil {
    private static final String SYS_TYPE_WINDOWS="windows";

    @Autowired
    private  DataXPropertiesConfig dataXPropertiesConfig;



    public void start(String jsonPath, String dataXHomePath){
        System.setProperty("datax.home",dataXHomePath);
        String[] datxArgs = {"-job", jsonPath,
                "-mode", "standalone",
                "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);
        } catch (Throwable var6) {
            log.error("\n\n经DataX智能分析,该任务最可能的错误原因是:\n" + ExceptionTracker.trace(var6));
        }
    }

    public void start(@NonNull String filePath,boolean isFullPath){
        Assert.hasText(filePath,">>>Error>>>:job filePath is null or empty<<<<<");
        String jsonPath=filePath;
        if(!isFullPath){
            String separator=SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType())?"\\":"/";
            jsonPath=dataXPropertiesConfig.getDataxJobFileDirectory()+separator+filePath;
        }

        System.setProperty("datax.home",dataXPropertiesConfig.getDataxHomePath());
        String[] datxArgs = {"-job", jsonPath,
                "-mode", "standalone",
                "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);
        } catch (Throwable var6) {
            log.error("\n\n经DataX智能分析,该任务最可能的错误原因是:\n" + ExceptionTracker.trace(var6));
            throw new RuntimeException("经DataX智能分析,该任务最可能的错误原因是:" + ExceptionTracker.trace(var6));
        }
    }
}
