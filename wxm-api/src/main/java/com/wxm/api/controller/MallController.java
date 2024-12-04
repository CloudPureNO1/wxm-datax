package com.wxm.api.controller;

import com.alibaba.fastjson.JSON;
import com.wxm.base.constrant.CharSetConstants;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.exception.*;
import com.wxm.core.annotation.ApiLog;
import com.wxm.core.annotation.OpLog;
import com.wxm.service.biz.mall.MallLoader;
import com.wxm.util.my.code.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-29 17:39:11
 */
@Slf4j
@RequestMapping("/mall")
@RestController
public class MallController {
    private final MallLoader mallLoader;


    public MallController(MallLoader mallLoader) {
        this.mallLoader = mallLoader;
    }


    @ApiLog(encryptType = EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"})
    @OpLog(value="商城:${transCode}",encryptType =EncryptTypeEnum.BASE64 ,encryptTypeObj ={"jsonStr"})
    @PostMapping(value = "/gateway/{transCode}")
    public DataRtn gateway(@PathVariable String transCode, @RequestBody String jsonStr, HttpServletRequest request) throws UnsupportedEncodingException, ApiException, JobException, BizSvcException, UtilException, DbSvcException, OSSException, EncodeException, DecodeException {
        jsonStr = jsonStr.replaceAll("[\\s*\t\n\r]", "");
        log.info("【商城】交易编号【{}】入参：{}", transCode, jsonStr);
        log.info("【商城】交易编号【{}】的token:", transCode,request.getHeader("accessToken;"));

        String jsonText = Base64Util.decode(jsonStr, CharSetConstants.UTF8);

        jsonText = jsonText.replaceAll("%7B", "{").replaceAll("%7D", "}")
                .replaceAll("%22", "\"").replaceAll("%3A", ":");
        log.info("【商城】交易编号【{}】入参：{}", transCode, jsonText);


        Object data =mallLoader.execute(transCode,jsonStr,request);

        DataRtn dataRtn = new DataRtn().success().setMessage("成功").data(data);

        log.info("【商城】交易编号【{}】出参：{}", transCode, JSON.toJSONString(dataRtn));

        return dataRtn;
    }
}
