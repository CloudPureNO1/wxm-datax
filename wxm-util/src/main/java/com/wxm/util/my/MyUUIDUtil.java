package com.wxm.util.my;

import java.util.UUID;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/27 14:23
 * @since 1.0.0
 */
public class MyUUIDUtil {
    private static final String BASE_CHARACTER = "1234567890_)(*&^%$#@!~`|\\}{][=-'\";:.,<>/?abcdefghigklmnopqrstuvwhyzABCDEFGHIGKLMNOPQRSTUVWHYZ";

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
    public static String uuid(String str) {
        String uuidStr = UUID.randomUUID().toString();
        return uuidStr+"-"+getRdStr(str);
    }

    private static String getRdStr(String str) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int quotient3 = str.length() / 3;
            int quotient2 = str.length() / 2;
            int remainder = str.length() % 3;
            int num = remainder;
            if (i <= quotient3) {
                if (i % 3 == 0) {
                    num = 2;
                } else if (i % 2 == 0) {
                    num = 1;
                } else {
                    num = (i / 4 < 2) && i > 3 ? (3 - remainder) : remainder;
                }
            } else if (i <= quotient2) {
                if (i % 2 == 0) {
                    num = 2;
                } else if (i % 3 == 0) {
                    num = 1;
                } else {
                    num = (i / 4 < 2) && i > 2 ? remainder : (3 - remainder);
                }
            } else {
                num = i % 5 == 0 ? (3 - remainder) : remainder;
            }
            if (num == 0) {
                num = 1;
            }
            int numAscii = (int) str.charAt(i) << num;
            if (numAscii == 0) {
                numAscii = i % 2 == 0 ? ((int) str.charAt(i)) + 1 : ((int) str.charAt(i)) + 2;
            }
            String cStr = String.valueOf(numAscii);
            if (BASE_CHARACTER.contains(Character.toString((char) numAscii))) {
                cStr = Character.toString((char) numAscii);
            }
            buf.append(cStr);
        }
        return buf.toString();
    }
}
