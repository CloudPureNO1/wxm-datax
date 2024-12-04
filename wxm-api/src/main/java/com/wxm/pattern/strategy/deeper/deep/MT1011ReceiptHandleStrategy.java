package com.wxm.pattern.strategy.deeper.deep;

/**
 * 具体策略类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:36:15
 */
public class MT1011ReceiptHandleStrategy implements ReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1011: " + receipt.getMessage());
    }
}
