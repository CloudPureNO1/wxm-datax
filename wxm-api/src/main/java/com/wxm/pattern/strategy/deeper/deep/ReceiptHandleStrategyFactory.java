package com.wxm.pattern.strategy.deeper.deep;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:37:52
 */
//策略工厂
public class ReceiptHandleStrategyFactory {
    private static final String RECEIPT_STRATEGY_PACKAGE = "com.wxm.test.main.pattern.strategy.deeper.deep";
    private static final String RECEIPT_STRATEGY_CLASS_PREFIX = "";
    private static final String RECEIPT_STRATEGY_CLASS_SUFFIX = "ReceiptHandleStrategy";
    private static final String PACKAGE_SEPARATOR = ".";

    public ReceiptHandleStrategyFactory() {
    }

    //使用Map集合存储策略信息,彻底消除if...else
    private static Map<String,ReceiptHandleStrategy> strategyMap;

    //初始化具体策略,保存到map集合
    public static void init(){
        strategyMap = new HashMap<>();
        strategyMap.put("MT1011",new MT1011ReceiptHandleStrategy());
        strategyMap.put("MT2101",new MT2101ReceiptHandleStrategy());
    }

    //根据回执类型获取对应策略类对象
    public static ReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        return strategyMap.get(receiptType);
    }

    //根据回执类型获取对应策略类对象
    public static ReceiptHandleStrategy getReceiptHandleStrategyNew(String receiptType) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (ReceiptHandleStrategy) getReceiptStrategyClass(receiptType).newInstance();
    }

    public static Class<?> getReceiptStrategyClass(String receiptType) throws ClassNotFoundException {
        return Class.forName(RECEIPT_STRATEGY_PACKAGE+PACKAGE_SEPARATOR+RECEIPT_STRATEGY_CLASS_PREFIX+receiptType+RECEIPT_STRATEGY_CLASS_SUFFIX);
    }
}
