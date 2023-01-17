package com.wxm.util.my;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 9:19
 * @since 1.0.0
 */
public class StringUtils {
    /**
     * 不要在方法里定义正则表达式规则,应定义为常量或字段,能加快正则匹配速度
     */
    private static Pattern pattern=Pattern.compile("[A-Z]");
    /**
     * 将字符串的首字母转大写
     * 这里先将字符串转为字符数组，然后将数组的第一个元素，即字符串首字母，进行ASCII 码前移，
     * ASCII 中大写字母从65开始，小写字母从97开始，所以这里减去32。
     * 如果首字母不是小写字母 不变
     * @param str 需要转换的字符串
     * @return
     */
    public static String toUpFirstCharacter(String str){
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] ch=str.toCharArray();
/*        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }*/
        ch[0]=ch[0]>='a'&&ch[0]<='z'?(char)(ch[0]-32):ch[0];
        return String.valueOf(ch);
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String transformHumpToUnderline(String str){
        // Pattern pattern=Pattern.compile("[A-Z]");
        if(str==null ||str.equals("")){
            return "";
        }
        StringBuilder builder=new StringBuilder(str);
        Matcher mc=pattern.matcher(str);
        int i=0;
        while(mc.find()){
            builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
            i++;
        }

        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}
