package com.wxm.api.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxm.base.enums.ApiEnum;
import com.wxm.base.exception.*;
import com.wxm.util.my.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/7 15:09
 * @since 1.0.0
 */
@Component
@Slf4j
public class Facade {
    private final ApplicationContext applicationContext;

    public Facade(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    private static final String[] SUB_TYPE_ARY = {"rbac", "quartz"};

    public Object gateway(String subType, String transCode, String jsonText) throws ApiException, DbSvcException, BizSvcException, UtilException, JobException, OSSException, EncodeException, DecodeException {
        if (Arrays.stream(SUB_TYPE_ARY).noneMatch(type -> type.equals(subType))) {
            log.error("【{}】{}，非正确地址内容为：{}", ApiEnum.API_1001.toString(), ApiEnum.API_1001.getMessage(), subType);
            throw new ApiException(ApiEnum.API_1001.toString(), ApiEnum.API_1001.getMessage() + "，非正确地址内容为：" + subType);
        }

        JSONObject json= JSON.parseObject(jsonText);
        if(json.isEmpty()||json.get("transCode")==null|| !org.springframework.util.StringUtils.hasLength(json.getString("transCode"))){
            log.error("【{}】{}:交易编码为空", ApiEnum.API_1002.toString(), ApiEnum.API_1002.getMessage());
            throw new ApiException(ApiEnum.API_1002.toString(), ApiEnum.API_1002.getMessage() + "，交易编码为空");
        }
        if(!transCode.equals(json.getString("transCode"))){
            log.error("【{}】{},交易编码有误：【{}】-【{}】", ApiEnum.API_1002.toString(), ApiEnum.API_1002.getMessage(),transCode,json.getString("transCode"));
            throw new ApiException(ApiEnum.API_1002.toString(), ApiEnum.API_1002.getMessage() + "，交易编码有误：【"+transCode+"】-【"+json.getString("transCode")+"】");
        }
        Iterator<String> its=json.keySet().iterator();
        while (its.hasNext()) {
            String key=its.next();
            Object value=json.get(key);
            if(value!=null&& value instanceof String){
                json.replace(key,((String) value).trim());
            }
        }

        // 注册的bean的名称位："sub"+subType  里面包含serve（String,String）实现方法
        // @Component("subRbac")
        Object obj = applicationContext.getBean("sub" + StringUtils.toUpFirstCharacter(subType));
        try {
            Method method = obj.getClass().getDeclaredMethod("serve", String.class, String.class);
            method.setAccessible(true);
            Object rs = method.invoke(obj, transCode, json.toJSONString());
            method.setAccessible(false);
            return rs;
        } catch (NoSuchMethodException e) {
            log.error("【{}】{}：{}", ApiEnum.API_1004.toString(), ApiEnum.API_1004.getMessage(), e.getMessage(),e);
            throw new ApiException(ApiEnum.API_1004.toString(), ApiEnum.API_1004.getMessage() + e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("【{}】{}：{}", ApiEnum.API_1005.toString(), ApiEnum.API_1005.getMessage(), e.getMessage(),e);
            throw new ApiException(ApiEnum.API_1005.toString(), ApiEnum.API_1005.getMessage() + e.getMessage());
        } catch (InvocationTargetException e) {
            if(e.getTargetException() instanceof DbSvcException){
                throw (DbSvcException)e.getTargetException();
            }
            if(e.getTargetException() instanceof BizSvcException){
                throw (BizSvcException)e.getTargetException();
            }
            if(e.getTargetException() instanceof UtilException){
                throw (UtilException)e.getTargetException();
            }
            if(e.getTargetException() instanceof JobException){
                throw (JobException)e.getTargetException();
            }
            if(e.getTargetException() instanceof OSSException){
                throw (OSSException)e.getTargetException();
            }
            if(e.getTargetException() instanceof EncodeException){
                throw (EncodeException)e.getTargetException();
            }
            if(e.getTargetException() instanceof DecodeException){
                throw (DecodeException)e.getTargetException();
            }
            if(e.getTargetException() instanceof ConstraintViolationException){
                throw (ConstraintViolationException)e.getTargetException();
            }
            String msg=e.getMessage();
            if(null==msg){
                msg=e.getTargetException().getMessage();
            }

            log.error("【{}】{}：{}", ApiEnum.API_1006.toString(), ApiEnum.API_1006.getMessage(), msg,e);
            throw new ApiException(ApiEnum.API_1006.toString(), ApiEnum.API_1006.getMessage() + msg);
        }
    }


    /**
     * 枚举所有的
     */
    @Autowired
    private SubRbac subRbac;
    @Autowired
    private SubQuartz subQuartz;

    public Object gatewayEnum(String subType, String transCode, String jsonText) throws ApiException, JobException, BizSvcException, UtilException, DbSvcException, OSSException, EncodeException, DecodeException {
        switch (subType) {
            case "Rbac":
                subRbac.serve(transCode, jsonText);
                break;
            case "Quartz":
                subQuartz.serve(transCode, jsonText);
                break;
            default:
                log.error("【{}】{}，非正确地址内容为：{}", ApiEnum.API_1001.toString(), ApiEnum.API_1001.getMessage(), subType);
                throw new ApiException(ApiEnum.API_1001.toString(), ApiEnum.API_1001.getMessage() + "，非正确地址内容为：" + subType);
        }
        return null;
    }
}
