package com.wxm.base.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-24 20:38:15
 */
@Data
public class ExceptionBean implements java.io.Serializable{
    private String message;
    List<StackTraceElement> list = new ArrayList<>();
}
