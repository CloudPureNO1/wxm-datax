package com.wxm.test.main;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;


/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/8 17:12
 * @since 1.0.0
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
       String str="ChromeFilled\n" +
               "Eleme\n" +
               "ElemeFilled\n" +
               "ElementPlus\n" +
               "Shop\n" +
               "SwitchFilled\n" +
               "WindPower";

       String [] rs=str.split("\n");
       System.out.println(JSON.toJSONString(rs));
    }




}
