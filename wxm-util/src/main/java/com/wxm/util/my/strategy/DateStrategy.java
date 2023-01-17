package com.wxm.util.my.strategy;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p></p>
 * <p></p>
 * yyyy-MM-dd HH:mm:ss SS
 *
 * @author 王森明
 * @date 2022/6/10 13:21
 * @since 1.0.0
 */
public class DateStrategy implements Strategy<Date> {
    @Override
    public Date parseToObject(String value) {
        if(null==value||!StringUtils.hasLength(value)){
            return null;
        }
        // 去掉所有空格  yyyy-MM-dd HH:mm:ss  ->  yyyy-MM-ddHH:mm:ss
        value = StringUtils.trimAllWhitespace(value);
        value = value.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "-")
                .replaceAll("时", ":").replaceAll("分", ":").replaceAll("秒", "");
        value=value.length()>=12?value.substring(0,10)+" "+value.substring(10):value;
        String fmt = "";
        switch (value.length()) {
            case 4:
                fmt = "yyyy";
                break;
            case 6:
                value = value.substring(0, 4) + "-" + value.substring(4);
                fmt = "yyyy-MM";
                break;
            case 7:
                value = value.replaceAll(":", "-").replaceAll("/", "-");
                fmt = "yyyy-MM";
                break;
            case 8:
                value = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6);
                fmt = "yyyy-MM-dd";
                break;
            case 10:
                fmt = "yyyy-MM-dd";
                break;
            case 13:
                //yyyy-MM-dd HH
                fmt="yyyy-MM-dd HH";
                break;
            case 16:
                fmt="yyyy-MM-dd HH:mm";
                break;
            case 19:
                fmt="yyyy-MM-dd HH:mm:ss";
                break;
            default:
                break;
        }
        if(!StringUtils.hasLength(fmt)){
            throw new RuntimeException("时间格式有误");
        }
        try {
            return new SimpleDateFormat(fmt).parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("【"+value+"】时间转换异常："+e.getMessage());
        }
    }



    @Override
    public String transToString(Date value) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
    }



}
