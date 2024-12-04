package com.wxm.api.facade;

import com.alibaba.fastjson.JSON;
import com.wxm.base.enums.ApiEnum;
import com.wxm.base.exception.*;
import com.wxm.service.biz.sys.IBizQuartzService;
import com.wxm.util.my.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>定时任务</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/7 15:10
 * @since 1.0.0
 */
@Slf4j
@Component("subQuartz")
public class SubQuartz {
    private static final String DTO_IN_PREFIX="com.wxm.base.dto.quartz.in.Quartz";
    private static final String DTO_IN_SUFFIX="In";

    private final IBizQuartzService bizQuartzService;

    public SubQuartz(IBizQuartzService bizQuartzService) {
        this.bizQuartzService = bizQuartzService;
    }

    public <T,E> E serve(String transCode,String jsonText) throws ApiException, DbSvcException, BizSvcException, UtilException, JobException, OSSException, EncodeException, DecodeException {
        try {
            // 取得dtoIn 的class
            Class<T>dtoClazz = (Class<T>) Class.forName(DTO_IN_PREFIX+ MyStringUtils.toUpFirstCharacter(transCode)+DTO_IN_SUFFIX);
            T t=JSON.parseObject(jsonText,dtoClazz);
            // 取得方法
            Method method =  bizQuartzService.getClass().getDeclaredMethod("service"+transCode,dtoClazz);
            return (E) method.invoke(bizQuartzService,dtoClazz.cast(t));
        } catch (ClassNotFoundException e) {
            log.error("【{}】{}：{}", ApiEnum.API_1003.toString(),ApiEnum.API_1003.getMessage(getMsg(transCode)),e.getMessage(),e);
            throw new ApiException(ApiEnum.API_1003.toString(),ApiEnum.API_1003.getMessage(getMsg(transCode))+":"+e.getMessage());
        } catch (NoSuchMethodException e) {
            log.error("【{}】{}：{}", ApiEnum.API_1004.toString(),ApiEnum.API_1004.getMessage(getMsg(transCode)),e.getMessage(),e);
            throw new ApiException(ApiEnum.API_1004.toString(),ApiEnum.API_1004.getMessage(getMsg(transCode))+":"+e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("【{}】{}：{}", ApiEnum.API_1005.toString(),ApiEnum.API_1005.getMessage(getMsg(transCode)),e.getMessage(),e);
            throw new ApiException(ApiEnum.API_1005.toString(),ApiEnum.API_1005.getMessage(getMsg(transCode))+":"+e.getMessage());
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
            log.error("【{}】{}：{}", ApiEnum.API_1006.toString(),ApiEnum.API_1006.getMessage(getMsg(transCode)),msg,e);
            throw new ApiException(ApiEnum.API_1006.toString(),ApiEnum.API_1006.getMessage(getMsg(transCode))+":"+msg);
        }
    }

    private String getMsg(String transCode){
        Map<String,String> typeMap=new HashMap<>();
        typeMap.put("1","");
        typeMap.put("2","");
        typeMap.put("3","");
        typeMap.put("4","");

        Map<String,String>opMap=new HashMap<>();
        opMap.put("1","查询");
        opMap.put("2","新增");
        opMap.put("3","修改");
        opMap.put("4","删除");
        opMap.put("4","定时任务的停止（quart）");
        opMap.put("4","恢复停止的定时任务（quart）");
        // 10001 12001 13001 14001
        // 20001 22001 23001 24001
        // 第一位： 1      2        3     4
        // 第二位： 1 查询  2 新增   3 修改 4 删除 ,5 quart定时任务的停止 6 恢复停止的定时任务
        String charFirst=transCode.substring(0,1);
        String charSecond=transCode.substring(1,2);
        return "定时任务,"+typeMap.get(charFirst)+opMap.get(charSecond);
    }
}
