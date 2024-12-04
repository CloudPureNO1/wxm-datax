package com.wxm.core.aspec;

import com.alibaba.fastjson.JSON;
import com.wxm.base.constrant.ApiConstant;
import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.enums.comm.ICommEnum;
import com.wxm.base.enums.comm.SubTypeEnum;
import com.wxm.base.enums.comm.factory.CommEnumFactory;
import com.wxm.core.annotation.OpLog;
import com.wxm.core.dto.FnDataDto;
import com.wxm.core.service.OpLogService;
import com.wxm.druid.entity.biz.WxmOperateLog;
import com.wxm.util.my.IPUtils;
import com.wxm.util.my.LogStackTraceUtil;
import com.wxm.util.my.strategy.decrypt.DecryptService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
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
@Component
public class OpLogAspect {
    private static ThreadLocal<WxmOperateLog> threadLocal = new ThreadLocal<>();
    private static final AtomicLong TEMPLATE_INDEX = new AtomicLong(0L);
    private static final ConcurrentMap<Method, Template> templates = new ConcurrentHashMap(32);

    private final DecryptService decryptService;

    private final OpLogService opLogService;

    public OpLogAspect(DecryptService decryptService, OpLogService opLogService) {
        this.decryptService = decryptService;
        this.opLogService = opLogService;
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

//        opLog.setLogId(UUID.randomUUID().toString());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 例如，获取名为"Authorization"的请求头
        String fnData = request.getHeader("fnData");
        log.info(">>>>>>>>>>>>>>>>>>>funData:{}",fnData);
        // 没有本地前端模块传入的fnData标识不是前端系统调用
        if(!StringUtils.hasText(fnData)){
            return joinPoint.proceed();
        }
        String decryptData = decryptService.decryptDataWithStrategy(fnData,EncryptTypeEnum.BASE64);
        FnDataDto fnDataDto = JSON.parseObject(decryptData, FnDataDto.class);


        WxmOperateLog opLog = new WxmOperateLog();
        opLog.setRequestUrl(request.getRequestURI());
        opLog.setOperatorIp(IPUtils.getIpAddr(request));
        opLog.setRequestTime(LocalDateTime.now());
        opLog.setResourceId(fnDataDto.getResourceId());
        opLog.setResourceUrl(fnDataDto.getResourceUrl());
        opLog.setResourceName(fnDataDto.getResourceName());


        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Signature signature = joinPoint.getSignature();

        //   joinPoint.getSignature().getDeclaringTypeName();    获取类名className
        //   joinPoint.getSignature().getName();    获取方法名methodName

        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Method currentMethod = methodSignature.getMethod();

        OpLog opLogAnnotation = (OpLog) currentMethod.getAnnotation(OpLog.class);
        String logTemplateTxt = opLogAnnotation.value();
        String[] jsonStr = opLogAnnotation.jsonStr();
        EncryptTypeEnum encryptTypeEnum = opLogAnnotation.encryptType();
        String[] encryptTypeObj = opLogAnnotation.encryptTypeObj();

        Template template = (Template) templates.get(currentMethod);
        if (template == null) {
            template = new Template("OpLog-" + TEMPLATE_INDEX.incrementAndGet(), new StringReader(logTemplateTxt), (Configuration) null);
            templates.putIfAbsent(currentMethod, template);
        }

        HashMap<String, Object> dataModel = new HashMap();
        int i = 0;
        if (parameterNames != null && parameterNames.length != 0) {
            for (i = 0; i < parameterNames.length; ++i) {
                String key = parameterNames[i];
                Object obj = args[i];

                if("request".equals(key)){
                    continue;
                }

                if (obj instanceof String) {
                    if(ApiConstant.API_TYPE.equals(key)){
                        // 设置subType的中文值
                        dataModel.put(key, SubTypeEnum.getMessage((String)obj));
                        continue;
                    }

                    String keyPre = parameterNames[i-1];
                    if(ApiConstant.TRANS_CODE.equals(key)){
                        Object objPre = args[i-1];

                        if (objPre instanceof String) {
                            ICommEnum commEnum=CommEnumFactory.getTypeEnum((String)objPre,(String)obj);
                            dataModel.put(key, commEnum.msg());
                        }else{
                            dataModel.put(key, obj);
                        }
                        continue;
                    }

                    // 解密
                    obj = decrypt(key, (String) obj, encryptTypeEnum, encryptTypeObj);
                    // json字符串转对象
                    obj = isJsonStr(key, jsonStr) ? JSON.parseObject((String) obj) : obj;


                    dataModel.put(key, obj);
                }

            }
        } else {
            for (i = 0; i < args.length; ++i) {
                dataModel.put("arg" + i, args[i]);
            }
        }

        if (result != null) {
            dataModel.put("result", result);
        }


        String opLogText = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataModel);

        log.info("opLogText:{}", opLogText);

        opLog.setRequestMethod(currentMethod.getName());
        opLog.setResponseTime(LocalDateTime.now());
        opLog.setOperatorInfo(opLogText);

        opLogService.setOperatorMsg(opLog);

        threadLocal.set(opLog);
        opLogService.saveLog(opLog);

        return result;
    }


    private String decrypt(String key, String str, EncryptTypeEnum encryptTypeEnum, String[] encodeObj) throws Exception {
        if (encryptTypeEnum != null && StringUtils.hasText(key) && encodeObj != null && encodeObj.length > 0 && Arrays.asList(encodeObj).stream().anyMatch(item -> item.equals(key))) {
            return decryptService.decryptDataWithStrategy(str, encryptTypeEnum);
        }
        return str;
    }

    private boolean isJsonStr(String str, String[] jsonStr) {
        return Arrays.asList(jsonStr).stream().anyMatch(item -> item.equals(str));
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
            opLogService.upLog(opLog);
        } catch (Exception ex) {
            log.info("异常：》》》》{}", ex.getMessage(), ex);
            throw new RuntimeException(ex);
        } finally {
            threadLocal.remove();
        }
    }


}
