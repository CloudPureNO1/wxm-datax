package com.wxm.util.my.code;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES128 加密工具类
 *
 * @throws
 * @Author 王森明-wangsm
 * @Date 2024-07-16 13:55
 * @version 1.0.0
 **/
@Slf4j
public class AESUtil {
    // 如果使用128位加密，将密钥截短至16字节。
//    private static final String KEYS = "INSIGMA-bigdata";
    //如果使用256位加密，将密钥扩展到32字节。
    private static final String KEYS = "INSIGMA-bigdata-V2-7-AES";



    /**
     * AES128 加密
     *
     * @param sSrc 加密字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc) throws Exception {
        if (sSrc == null || sSrc.trim().equals("")) {
            return null;
        }
        byte[] raw = KEYS.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 解密
     *
     * @param sSrc
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-17 10:18
     * @version 1.0.0
     **/
    public static String decrypt(String sSrc) throws Exception {

        byte[] raw = KEYS.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = Base64.getDecoder().decode(sSrc);//先用base64解密

        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        log.info(originalString);
        return originalString;

    }






    public static void main(String [] args) {

        try {
//            String s = "(COLUMN_NAME like '%AAC003%' or COLUMN_NAME like 'NAME%') or (COLUMN_COMMENT like '%姓名%')";
//            String encode = encrypt(s);
//            System.out.println("原文：" + s);

            String enStr="EM3NKlwq2nD1f/4zH854k8tihjoCc7npe/GE99SFOJrXHVY8tdasl0T0BO1qtcO7rxPxprVaW8L+rvBsO95aMd3kIBdrHczi4ByJqBKJTHZjhRURF83Pla9dtFa4SZHMyawF0bH0R/ungkJ5HWfUovSQ9t+0af7FJHGK1UOEjo+9f5L0LDRNEIWptqO+PgGRenR/RGrLqw2TeO6+dOrHtGOq+VooviVprSpNHXgsgXk";
            System.out.println("密文：" + enStr);
            String decode = decrypt(enStr);
            System.out.println("明文：" + decode);

//            String test="(COLUMN_NAME like 'AAC002%' or COLUMN_NAME like 'AAE135%' or COLUMN_NAME like '%ID_CARD%' or COLUMN_NAME like 'CITIZEN_ID%') or (COLUMN_COMMENT like '%\\u8EAB\\u4EFD\\u8BC1%')\\uasdfd";
//            System.out.println("明文：" + UnicodeUtil.decodeUnicode(test));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
