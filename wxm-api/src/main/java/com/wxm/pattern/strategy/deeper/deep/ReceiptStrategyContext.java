package com.wxm.pattern.strategy.deeper.deep;

/**
 * 上下文类,持有策略接口
 * 策略上下文类(策略接口的持有者)
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:37:01
 */

public class ReceiptStrategyContext {

    private ReceiptHandleStrategy receiptHandleStrategy;

    public  ReceiptStrategyContext(ReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    public void setReceiptHandleStrategy(ReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    //调用策略类中的方法
    public void handleReceipt(Receipt receipt) {
        if (receipt != null) {
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }
}