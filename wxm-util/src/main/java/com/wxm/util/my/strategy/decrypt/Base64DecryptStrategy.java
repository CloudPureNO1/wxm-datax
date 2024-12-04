package com.wxm.util.my.strategy.decrypt;

import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.exception.DecodeException;
import com.wxm.util.my.code.Base64Util;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-14 17:17:51
 */
@Component
public class Base64DecryptStrategy implements DecryptStrategy {
    @Override
    public EncryptTypeEnum type() {
        return EncryptTypeEnum.BASE64;
    }

    @Override
    public String decrypt(String encryptedData) throws DecodeException {
        return Base64Util.decode(encryptedData);
    }

    @Override
    public String decrypt(String encryptedData, String charset) throws DecodeException {
        return Base64Util.decode(encryptedData,charset);
    }
}
