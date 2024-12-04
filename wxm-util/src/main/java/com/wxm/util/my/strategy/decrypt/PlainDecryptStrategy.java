package com.wxm.util.my.strategy.decrypt;

import com.wxm.base.enums.EncryptTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 明文
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-14 17:28:24
 */
@Component
public class PlainDecryptStrategy implements DecryptStrategy{
    @Override
    public EncryptTypeEnum type() {
        return EncryptTypeEnum.PLAIN;
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        return encryptedData;
    }

    @Override
    public String decrypt(String encryptedData, String charset) throws Exception {
        return encryptedData;
    }
}
