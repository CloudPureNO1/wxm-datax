package com.wxm.util.my.strategy.decrypt;

import com.wxm.base.enums.EncryptTypeEnum;
import com.wxm.base.exception.DecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-14 17:33:54
 */
@Service
public class DecryptService {
    private final List<DecryptStrategy> strategies;

    public DecryptService(List<DecryptStrategy> strategies) {
        this.strategies = strategies;
    }

    public String decryptDataWithStrategy(String data, EncryptTypeEnum encryptTypeEnum) throws Exception {
        for (DecryptStrategy strategy : strategies) {
            // 假设策略有一个getName()方法可以用来标识它，或者你有其他方式区分策略
            if (strategy.type()==encryptTypeEnum) {
                return strategy.decrypt(data);
            }
        }
        throw new DecodeException("没有对应的解密策略: " + encryptTypeEnum.name());
    }
}
