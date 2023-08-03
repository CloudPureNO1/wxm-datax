package com.wxm.push.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-10 16:28:48
 */
@Accessors(chain = true)
@Data
public class MsgDtoIn implements java.io.Serializable{

    /**
     * 信息
     */
    private String msg;

    /**
     * 消息接收者
     */
    private List<String> listUid=new ArrayList<>();

}
