package com.wxm.util.my.strategy.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统一校验类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-15 10:23:50
 */
public class Validator extends cn.hutool.core.lang.Validator {

    /**
     * 地址校验
     *
     * @param sequence
     * @return boolean
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-15 10:26
     * @version 1.0.0
     **/
    public static boolean isAddress(CharSequence sequence) {
        String addr = sequence.toString().replaceAll("\\?", "").replaceAll("？", "");
        String regexEnNum = "^(\\d+\\s+)?([a-zA-Z]+(\\s+[a-zA-Z]+)*)(,\\s+[a-zA-Z]+){2}(\\s+\\d{5})?$";
        String regexZh =  "^([\\u4e00-\\u9fa5\\d]+(?:区|县|市|省|镇|乡|村|街|路|巷|弄|里|园|苑|小区|大厦|号楼|楼|单元|室|座|号)?\\s*)*[\\u4e00-\\u9fa5\\d]+(?:号|幢|座|层|室|单元|楼|号楼)?(\\d+[-]?\\d*)?";;

        // 用于存储结果的共享变量
        final boolean[] result = new boolean[]{false};

        Thread thread = new Thread(() -> {
            Pattern patternEnNum = Pattern.compile(regexEnNum);
            Pattern patternZh = Pattern.compile(regexZh);
            Matcher matcherEnNum = patternEnNum.matcher(addr);
            Matcher matcherZh = patternZh.matcher(addr);
            result[0] = matcherEnNum.matches() || matcherZh.matches();
        });

        thread.start();

        try {
            // 等待线程完成或超时
            thread.join(100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }

        return result[0];

    }

    /**
     * 公司名称校验
     *
     * @param sequence
     * @return boolean
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-15 10:26
     * @version 1.0.0
     **/
    public boolean isCompanyName(CharSequence sequence) {
        // 正则表达式匹配英文名称
        String regexEnglish = "^[a-zA-Z\\s-]+(,?[a-zA-Z\\s-]+)*$";
        // 正则表达式匹配中文名称
        String regexChinese = "^[\u4e00-\u9fa5]+$";

        boolean isEnglishValid = sequence.toString().matches(regexEnglish);
        boolean isChineseValid = sequence.toString().matches(regexChinese);

        return isEnglishValid || isChineseValid;
    }


    public static void main(String[] args){
//        String str="佛山市南海区狮山镇兴贤路段佛山市同心珠宝首饰有限公司新厂区(一期工?";
        String str="佛山市南海区大沥横岗工业区(土名:暗岗";
        System.out.println("::::::::>>>>>>>>>>"+isAddress(str));



    }
}
