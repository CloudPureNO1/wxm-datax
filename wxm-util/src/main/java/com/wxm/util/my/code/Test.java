package com.wxm.util.my.code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 12:03
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String str="{\n" +
                "    \"transCode\":\"1111\"\n" +
                "}";
        encode(str);
    }

    public static void encode(String str){
        String encodedStr=new String(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        System.out.println("****************明文："+str);
        System.out.println("****************密文:"+encodedStr);
    }
    public static void decode(String str) throws IOException {
        byte[] buytes=Base64.getDecoder().decode(str);
        String decodedStr=new String(buytes,"utf-8");
        System.out.println("****************密文："+str);
        System.out.println("****************明文:"+decodedStr);
    }
}
