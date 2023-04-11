package com.wxm.push.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxm.push.server.WebsocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * websocket
 * 消息推送(个人和广播)
 */
@RequestMapping("/push")
@Controller
public class WebSocketController {

    private final WebsocketServer ws;

    public WebSocketController(WebsocketServer ws) {
        this.ws = ws;
    }


    /**
     * 服务端页面
     *
     * @return
     */
    @RequestMapping(value = "/getOnlineUsers")
    @ResponseBody
    public Map<String,Object> admin() {
        int num = ws.getOnlineNum();
        List<String> list = ws.getOnlineUsers();

        Map<String,Object> map=new HashMap<>();
        map.put("onlineNum",num);
        map.put("onLineUserList",list);
        return map;
    }

    /**
     * 个人信息推送
     *
     * @return
     */
    @PostMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(@RequestBody String jsonStr) {
        //第一个参数 :msg 发送的信息内容
        //第二个参数为用户长连接传的用户人数
        JSONObject json = JSON.parseObject(jsonStr);
        String msg=json.getString("msg");
        JSONArray jsonArray=json.getJSONArray("userList");
        List<String>userList=jsonArray.toJavaList(String.class);
        ws.SendMany(msg, userList);
        return "success";
    }

    /**
     * 推送给所有在线用户
     *
     * @return
     */
    @PostMapping("/sendAll")
    @ResponseBody
    public String sendAll(@RequestParam("msg") String msg) {
        ws.sendAll(msg);
        return "success";
    }
}
