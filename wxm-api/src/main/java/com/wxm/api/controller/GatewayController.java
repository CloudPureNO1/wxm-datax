package com.wxm.api.controller;

import com.alibaba.fastjson.JSON;
import com.wxm.api.facade.Facade;
import com.wxm.api.service.IGatewayService;
import com.wxm.base.constrant.CharSetConstants;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.exception.*;
import com.wxm.core.annotation.ApiLog;
import com.wxm.core.annotation.OpLog;
import com.wxm.util.my.code.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyleConstants;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/5 17:16
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/")
public class GatewayController {
    private final IGatewayService gatewayService;

    private final Facade facade;

    public GatewayController(Facade facade, IGatewayService gatewayService) {
        this.facade = facade;
        this.gatewayService = gatewayService;
    }




    @ApiLog(encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"})
    @OpLog(value="${subType}:${transCode}",encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"})
    @PostMapping(value = "/gateway/{subType}/{transCode}")
    public DataRtn gateway(@PathVariable String subType, @PathVariable String transCode, @RequestBody String jsonStr, HttpServletRequest request) throws UnsupportedEncodingException, ApiException, JobException, BizSvcException, UtilException, DbSvcException, OSSException, EncodeException, DecodeException {
        jsonStr = jsonStr.replaceAll("[\\s*\t\n\r]", "");
        log.info("【{}】交易编号【{}】入参：{}", subType, transCode, jsonStr);
        log.info("【{}】交易编号【{}】的token:",subType, transCode,request.getHeader("accessToken;"));
        //byte[] bytes = Base64.getDecoder().decode(jsonStr);
        // String jsonText = new String(bytes, "utf-8");
        String jsonText = Base64Util.decode(jsonStr, CharSetConstants.UTF8);
        // 异常交由统一异常处理
        // Object data=facade.gatewayEnum(subType,transCode,jsonText);
        // transCode%3A11001
        jsonText = jsonText.replaceAll("%7B", "{").replaceAll("%7D", "}")
                .replaceAll("%22", "\"").replaceAll("%3A", ":");
        log.info("【{}】交易编号【{}】入参：{}", subType, transCode, jsonText);
        Object data = facade.gateway(subType, transCode, jsonText);


        DataRtn dataRtn = new DataRtn().success().setMessage("成功").data(data);
        
        log.info("【{}】交易编号【{}】出参：{}", subType, transCode, JSON.toJSONString(dataRtn));

        return dataRtn;
    }



    @OpLog(value="新增定时任务",encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"})
    @RequestMapping("/addJob")
    public DataRtn addJob(@NotBlank(message = "定时任务内容不能为空") @RequestBody String jsonStr) throws IOException, BaseException {
        byte[] bytes = Base64.getDecoder().decode(jsonStr);
        String text = new String(bytes, "utf-8");
        String jobFilePath = gatewayService.addJob(text);
        return new DataRtn().success().setData(jobFilePath).setMessage("新增任务成功");
    }





    @ApiLog(encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"name"})
    @OpLog("测试1111:${name}-----${subType}:${transCode}")
    @GetMapping("/log/test/{subType}/{transCode}")
    public DataRtn test(@PathVariable String subType,@PathVariable String transCode,@RequestParam String name){
        return DataRtn.ok();
    }

    @OpLog(value="测试1111:${jsonStr.name}-----${id}-----${jsonStr.age}",jsonStr = {"jsonStr","test"})
    @PostMapping("/log/test/wsm/{id}")
    public DataRtn testPost(@PathVariable String id,@RequestBody String jsonStr){
        return DataRtn.ok();
    }

    @OpLog(value="测试1111:${jsonStr.name}-----${id}-----${jsonStr.age}",jsonStr = {"jsonStr","test"},encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"} )
    @PostMapping("/log/test/wxm/{id}")
    public DataRtn testPost1(@PathVariable String id,@RequestBody String jsonStr){
        return DataRtn.ok();
    }
}
