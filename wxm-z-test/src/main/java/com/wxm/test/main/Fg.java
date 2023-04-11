package com.wxm.test.main;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-07 14:29:05
 */
@Slf4j
public class Fg {
    public static void main(String[] args) throws ParseException {
        long yMillis =365*24*60*60*1000;
       double d=  (Math.pow(2,40))/yMillis;
       System.out.println(d);
       System.out.println(">>>>>>>>>>>>>>");

       long nowMillis = System.currentTimeMillis();
       System.out.println(yMillis);
       System.out.println(yMillis*69);
       System.out.println(yMillis*69);
       System.out.println(nowMillis/yMillis);
       System.out.println(Long.toBinaryString(System.currentTimeMillis()));
       System.out.println(Long.toBinaryString(System.currentTimeMillis()).length());

       long n=2;
       System.out.println(Long.toBinaryString(n));

       log.info("{},{},{}",yMillis,yMillis*69,System.currentTimeMillis());


       System.out.println(">>>>>>>>>"+binaryToDecimal("11000011101011010100000010001010000000101"));


       Date date=new Date();
       System.out.println(date.getTime());


       String dd="2022-02-02 22:22:22";
       Date ddd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dd);
       System.out.println(ddd);
       long time=ddd.getTime();
       System.out.println(time);


       long num=t(2,40);
       log.info("2 的 41 次幂为：{}",num);
       log.info("年限：{}",(num-1)/1000/60/60/24/365);


       log.info(">>>>>>>>>>>>>>>{}",(1L << 41) / (1000L * 60 * 60 * 24 * 365));
    }

    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(binary.charAt(i));
            decimal += digit * Math.pow(2, power);
            power++;
        }
        return decimal;
    }


    public static  long t(int b,int n){
        long num=b;
        for(int i=0;i<n;i++){
            num*=b;
        }
        return num;
    }
}
