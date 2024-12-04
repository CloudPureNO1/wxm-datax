package com.wxm.util.my.code;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-17 11:51:21
 */
public class UnicodeUtil {

    /**
     * 解析unicode
     *
     * @param input
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-17 9:35
     * @version 1.0.0
     **/
    public static String decodeUnicode(String input) {
        int start = 0;
        int end = 0;
        StringBuilder output = new StringBuilder();

        while (start < input.length()) {
            if (input.charAt(start) == '\\') {
                start++; // 跳过 \
                if (input.charAt(start) == 'u') {
                    start++; // 跳过 u
                    end = start + 4; // Unicode编码是4位的

                    // 尝试解析Unicode编码
                    if (end <= input.length() && isHexDigit(input.charAt(end - 1))) {
                        try {
                            output.append((char) Integer.parseInt(input.substring(start, end), 16));
                            start = end;
                        } catch (NumberFormatException ignored) {
                            // 如果无法解析，跳过此步并保留原始字符串
                            output.append(input.substring(start - 2, end)); // 包括\\u在内的5个字符
                            start = end;
                        }
                    } else {
                        // 如果不是有效的Unicode编码格式，直接添加原始字符
                        output.append(input.charAt(start - 1)); // 添加反斜杠
                        start--;
                    }
                } else {
                    output.append(input.charAt(start));
                    start++;
                }
            } else {
                output.append(input.charAt(start));
                start++;
            }
        }
        return output.toString();
    }

    /**
     * 是否16进制
     *
     * @param c
     * @return boolean
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-17 9:35
     * @version 1.0.0
     **/
    private static boolean isHexDigit(char c) {
        return ('0' <= c && c <= '9') || ('A' <= c && c <= 'F') || ('a' <= c && c <= 'f');
    }

}
