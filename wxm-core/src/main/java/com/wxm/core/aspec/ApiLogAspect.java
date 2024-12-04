package com.wxm.core.aspec;

import com.alibaba.fastjson.JSON;
import com.wxm.base.constrant.ApiConstant;
import com.wxm.base.constrant.LogConstant;
import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.enums.comm.ICommEnum;
import com.wxm.base.enums.comm.LogStatusEnum;
import com.wxm.base.enums.comm.SubTypeEnum;
import com.wxm.base.enums.comm.factory.CommEnumFactory;
import com.wxm.core.annotation.ApiLog;
import com.wxm.core.annotation.OpLog;
import com.wxm.core.service.ApiLogService;
import com.wxm.core.service.OpLogService;
import com.wxm.druid.entity.biz.WxmApiLog;
import com.wxm.druid.entity.biz.WxmOperateLog;
import com.wxm.util.my.IPUtils;
import com.wxm.util.my.LogStackTraceUtil;
import com.wxm.util.my.MyUUIDUtil;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 接口日志
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-13 16:38:07
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {
    private static ThreadLocal<WxmApiLog> threadLocal = new ThreadLocal<>();
    private static final AtomicLong TEMPLATE_INDEX = new AtomicLong(0L);
    private static final ConcurrentMap<Method, Template> templates = new ConcurrentHashMap(32);

    private final DecryptService decryptService;

    private final ApiLogService apiLogService;

    public ApiLogAspect(DecryptService decryptService,ApiLogService apiLogService) {
        this.decryptService=decryptService;
        this.apiLogService=apiLogService;
    }


    @Pointcut("@annotation(com.wxm.core.annotation.ApiLog)")
    private void ApiLogAspect() {
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
    @Around("ApiLogAspect()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        WxmApiLog apiLog = new WxmApiLog();
        apiLog.setLogId(MyUUIDUtil.uuid());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        apiLog.setTransUrl(request.getRequestURI());
        apiLog.setIp(IPUtils.getIpAddr(request));
        apiLog.setRequestTime(LocalDateTime.now());


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

        ApiLog apiLogAnnotation = (ApiLog) currentMethod.getAnnotation(ApiLog.class);
        EncryptTypeEnum encryptTypeEnum = apiLogAnnotation.encryptType();
        String[] encryptTypeObj = apiLogAnnotation.encryptTypeObj();


        Map<String,Object> params = new HashMap<>();
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
                        apiLog.setApiType((String)obj);

                        String typeName=SubTypeEnum.getMessage((String)obj);

                        String keyTransCode = parameterNames[i+1];
                        if(ApiConstant.TRANS_CODE.equals(keyTransCode)){
                            Object objTransCode = args[i+1];

                            if (objTransCode instanceof String) {
                                apiLog.setTransCode((String)objTransCode);

                                ICommEnum commEnum= CommEnumFactory.getTypeEnum((String)obj,(String)objTransCode);
                                apiLog.setTransName(typeName+"："+commEnum.msg());
                            }
                        }
                    }

                    // 解密
                    String objDec = decrypt(key, (String) obj, encryptTypeEnum, encryptTypeObj);

//                    args[i]=objDec;

                    params.put(key,objDec);

                }
            }
        }




        apiLog.setResponseTime(LocalDateTime.now());
        apiLog.setExecutionTime(executionTime+"");

        apiLog.setMethodName(currentMethod.getName());
        apiLog.setClassName(signature.getDeclaringTypeName());
        // 状态：1 请求 2 响应 3 异常
        apiLog.setState(LogStatusEnum.RESPONSE.getCode());

        apiLog.setCaller("");
        apiLog.setDataIn(JSON.toJSONString(params.isEmpty()?args:params));
        if(result instanceof String){
            apiLog.setDataoOut(decryptService.decryptDataWithStrategy((String)result,encryptTypeEnum));
        }else{
            apiLog.setDataoOut(JSON.toJSONString(result));
        }


        apiLogService.setOperatorMsg(apiLog);

        threadLocal.set(apiLog);

        apiLogService.saveLog(apiLog);

        return result;
    }


    private String decrypt(String key, String str, EncryptTypeEnum encryptTypeEnum, String[] encodeObj) throws Exception {
        if (encryptTypeEnum != null && StringUtils.hasText(key) && encodeObj != null && encodeObj.length > 0 && Arrays.asList(encodeObj).stream().anyMatch(item -> item.equals(key))) {
            return decryptService.decryptDataWithStrategy(str, encryptTypeEnum);
        }
        return str;
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
    @AfterThrowing(pointcut = "ApiLogAspect()", throwing = "e")
    public void afterthrowing(Throwable e) {
        try {
            WxmApiLog apiLog = threadLocal.get();
            apiLog.setExceptionTime(LocalDateTime.now());
            apiLog.setExceptionInfo(JSON.toJSONString(LogStackTraceUtil.buildData(e)));
            apiLog.setState(LogStatusEnum.EXCEPTION.getCode());
            apiLogService.upLog(apiLog);
        } catch (Exception ex) {
            log.info("异常：》》》》{}", ex.getMessage(), ex);
            throw new RuntimeException(ex);
        } finally {
            threadLocal.remove();
        }
    }

}
