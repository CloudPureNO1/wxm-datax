package com.wxm.core.aspec;

import com.alibaba.fastjson.JSON;
import com.wxm.druid.entity.biz.WxmOperateLog;
import com.wxm.druid.mapper.biz.WxmOperateLogMapper;
import com.wxm.util.my.LogStackTraceUtil;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-13 16:39:01
 */
@Slf4j
@Aspect
//@Component
public class OpLogAspectBAK {
    private static ThreadLocal<WxmOperateLog> threadLocal = new ThreadLocal<>();
    private static final AtomicLong TEMPLATE_INDEX = new AtomicLong(0L);
    private static final ConcurrentMap<Method, Template> syslogTemplates = new ConcurrentHashMap(32);

    private final WxmOperateLogMapper wxmOperateLogMapper;
    private final FreeMarkerConfigurer freeMarkerConfigurer;
    public OpLogAspectBAK(WxmOperateLogMapper wxmOperateLogMapper, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.wxmOperateLogMapper = wxmOperateLogMapper;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }


    @Pointcut("@annotation(com.wxm.core.annotation.OpLog)")
    private void OpLogAspect() {
    }


    /**
     * 环绕
     *
     * @param joinPoint
     * @return java.lang.Object
     * @throws
     * @Author 王森明-wangsm
     * @date 2023-05-11 9:48:44
     * @version 1.0.0
     **/
    @Around("OpLogAspect()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // 准备模板数据
        Map<String, Object> model = new HashMap<>();
        model.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        model.put("level", "INFO");
        model.put("className", joinPoint.getSignature().getDeclaringTypeName());
        model.put("methodName", joinPoint.getSignature().getName());
        model.put("arguments", JSON.toJSONString(joinPoint.getArgs()));
        model.put("result", JSON.toJSONString(result));
        model.put("executionTime", executionTime);
        Map<String,String>person=new HashMap<>();
        person.put("name","wsm");
        model.put("person",person);

        // 使用FreeMarker生成日志内容
        String logContent = processTemplate("ApiLog.ftl", model);

        // 打印或存储日志内容
        System.out.println(logContent);

        return result;
    }


    /**
     * 异常
     *
     * @param e
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @date 2023-05-11 9:48:44
     * @version 1.0.0
     **/
    @AfterThrowing(pointcut = "OpLogAspect()", throwing = "e")
    public void afterthrowing(Throwable e) {
        try {
            WxmOperateLog opLog = threadLocal.get();
            opLog.setExceptionTime(LocalDateTime.now());
            opLog.setExceptionInfo(JSON.toJSONString(LogStackTraceUtil.buildData(e)));
            wxmOperateLogMapper.updateById(opLog);
        } catch (Exception ex) {
            log.info("异常：》》》》{}", ex.getMessage(), ex);
        } finally {
            threadLocal.remove();
        }
    }


    private String processTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process FreeMarker template", e);
        }
    }
}
