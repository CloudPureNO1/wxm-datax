package com.wxm.pattern.strategy.deeper.deep;

/**
 * 回执处理策略接口
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:35:17
 */

public interface ReceiptHandleStrategy {

    void handleReceipt(Receipt receipt);
}