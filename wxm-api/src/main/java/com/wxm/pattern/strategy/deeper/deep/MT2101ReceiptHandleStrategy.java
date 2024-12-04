package com.wxm.pattern.strategy.deeper.deep;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:36:44
 */
public class MT2101ReceiptHandleStrategy implements ReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101: " + receipt.getMessage());
    }
}
