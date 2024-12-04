package com.wxm.util.my.strategy.decrypt;

import com.wxm.base.enums.EncryptTypeEnum;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-14 17:10:20
 */
public interface DecryptStrategy {
    /**
     * 类型
     * @return
     */
    EncryptTypeEnum type();


    /**
     * 解密
     * @param encryptedData
     * @return
     * @throws Exception
     */
    String decrypt(String encryptedData) throws Exception;

    /**
     * 解密
     * @param encryptedData
     * @param charset
     * @return
     * @throws Exception
     */
    String decrypt(String encryptedData, String charset) throws Exception;
}
