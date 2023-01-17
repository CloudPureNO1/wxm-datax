package com.wxm.util.my.code;

import com.wxm.ENCODE.enums.EncodeEnum;
import com.wxm.base.constrant.CharSetConstants;

import com.wxm.base.enums.DecodeEnum;
import com.wxm.base.exception.DecodeException;
import com.wxm.base.exception.EncodeException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


/**
 * <p>BASE64帮助类</p>
 *
 * @author wangsm
 * @date 2020-11-05
 * @sence 1.0.0
 */
@Slf4j
public class Base64Util {

    public static String decode(String str) throws DecodeException {
        return decode(str, CharSetConstants.UTF8);
    }


    public static String decode(String str, String charset) throws DecodeException {
        try {
            return new String(Base64.getDecoder().decode(str), charset);
        } catch (UnsupportedEncodingException e) {
            log.error("【{}】{}：{}", DecodeEnum.DECODE_1006.toString(), DecodeEnum.DECODE_1006.getMessage(), e.getMessage());
            throw new DecodeException(DecodeEnum.DECODE_1006.toString(), DecodeEnum.DECODE_1006.getMessage(), e.getMessage());
        }catch (Exception e){
            log.error("【{}】{}：{}", DecodeEnum.DECODE_1007.toString(), DecodeEnum.DECODE_1007.getMessage(), e.getMessage());
            throw new DecodeException(DecodeEnum.DECODE_1007.toString(),DecodeEnum.DECODE_1007.getMessage(), e.getMessage());
        }
    }


    public static String encode(String str) throws EncodeException {
        return encode(str, CharSetConstants.UTF8);
    }

    public static String encode(String str, String charset) throws EncodeException {
        try {
            return new String(Base64.getEncoder().encode(str.getBytes()),charset);
        } catch (UnsupportedEncodingException e) {
            log.error("【{}】{}：{}", EncodeEnum.ENCODE_1006.toString(), EncodeEnum.ENCODE_1006.getMessage(), e.getMessage());
            throw new EncodeException(EncodeEnum.ENCODE_1006.toString(), EncodeEnum.ENCODE_1006.getMessage(), e.getMessage());
        }catch (Exception e){
            log.error("【{}】{}：{}", EncodeEnum.ENCODE_1007.toString(), EncodeEnum.ENCODE_1007.getMessage(), e.getMessage());
            throw new EncodeException(EncodeEnum.ENCODE_1007.toString(),EncodeEnum.ENCODE_1007.getMessage(), e.getMessage());
        }
    }


}
