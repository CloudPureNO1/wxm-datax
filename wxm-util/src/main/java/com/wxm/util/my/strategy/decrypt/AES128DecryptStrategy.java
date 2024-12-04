package com.wxm.util.my.strategy.decrypt;

import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.util.my.code.AES128Util;
import org.springframework.stereotype.Component;

/**
 * AES128解密 策略
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-14 17:23:27
 */
@Component
public class AES128DecryptStrategy implements DecryptStrategy{
    @Override
    public EncryptTypeEnum type() {
        return EncryptTypeEnum.AES128;
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        return AES128Util.encodeAES128(encryptedData);
    }

    @Override
    public String decrypt(String encryptedData, String charset) throws Exception {
        return AES128Util.decodeAES128(encryptedData, charset);
    }
}
