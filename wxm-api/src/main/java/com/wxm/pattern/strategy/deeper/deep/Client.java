package com.wxm.pattern.strategy.deeper.deep;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:38:27
 */
//客户端
public class Client {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {


        List<Receipt> receiptList = ReceiptBuilder.genReceiptList();



        ReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategyNew("MT3101");
        //策略上下文
        ReceiptStrategyContext context = new ReceiptStrategyContext(receiptHandleStrategy);
        context.handleReceipt(receiptList.get(0));


        //模拟回执




        //策略模式将策略的 定义、创建、使用这三部分进行了解耦
        for (Receipt receipt : receiptList) {
            //获取置策略
            ReceiptHandleStrategyFactory.init();
            ReceiptHandleStrategy strategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            //设置策略
            context.setReceiptHandleStrategy(strategy);
            //执行策略
            context.handleReceipt(receipt);
        }



    }
}
