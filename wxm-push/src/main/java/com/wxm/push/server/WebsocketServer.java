package com.wxm.push.server;

import com.alibaba.fastjson.JSON;
import com.wxm.push.dto.Client;
import com.wxm.push.dto.MsgDtoIn;
import com.wxm.push.dto.MsgDtoOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Websocket Server 参数 uid 对应为 {@link Client}
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-10 16:55:54
 */
@Slf4j
@ServerEndpoint(value = "/ws-push-socket/{uid}")
@Component
public class WebsocketServer {

    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     **/
    private static CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket封装的session,信息推送，就是通过它来信息推送
     */
    private Session session;


    /**
     * 原始超级管理员登录，可以监控所有消息
     * Original super administrator of the system
     */
    private final static String SYS_ORIGINAL_USERNAME = "wxm";

    /**
     * 用户连接时触发，我们将其添加到
     * 保存客户端连接信息的socketServers中
     *
     * @throws
     * @Param session
     * @Param uid
     * @Return void
     * @Author wangsm
     * @Date 2023/3/10 17:29
     * @version 1.0.0
     **/
    @OnOpen
    public void open(Session session, @PathParam(value = "uid") String uid) throws IOException {
        this.session = session;
        socketServers.add(new Client(uid, session));
        log.info("客户端:【{}】连接成功", uid);

        if (!CollectionUtils.isEmpty(socketServers)) {
            sendAll("用户【" + uid + "】上线");
        }
    }

    /**
     * 收到客户端发送信息时触发
     *
     * @param msgData
     */
    @OnMessage
    public void onMessage(String msgData, @PathParam(value = "uid") String uid) throws IOException {
        MsgDtoIn in = JSON.parseObject(msgData, MsgDtoIn.class);
        // 用于监控消息
        sendMessage(in.getMsg(), uid, in.getListUid());

        Client client = socketServers.stream().filter(cli -> cli.getSession() == session).collect(Collectors.toList()).get(0);
        log.info("用户:【{}】发送信息:{}", client.getUid(), msgData);
    }

    /**
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose() throws IOException {
        for(Client client:socketServers) {
            if (client.getSession().getId().equals(session.getId())) {
                log.info("客户端:【{}】断开连接", client.getUid());
                socketServers.remove(client);
                if (!CollectionUtils.isEmpty(socketServers)) {
                    sendAll("用户[" + client.getUid() + "]下线");
                }
            }
        }
    }

    /**
     * 发生错误时触发
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                socketServers.remove(client);
                log.error("客户端:【{}】发生异常", client.getUid(),error);
            }
        });
    }


    /**
     * 获取服务端当前客户端的连接数量，
     * 因为服务端本身也作为客户端接受信息，
     * 所以连接总数还要减去服务端
     * 本身的一个连接数
     * <p>
     * 这里运用三元运算符是因为客户端第一次在加载的时候
     * 客户端本身也没有进行连接，-1 就会出现总数为-1的情况，
     * 这里主要就是为了避免出现连接数为-1的情况
     *
     * @return
     */
    public synchronized int getOnlineNum() {
        return socketServers.size();
    }

    /**
     * 获取在线用户名，前端界面需要用到
     *
     * @return
     */
    public synchronized List<String> getOnlineUsers() {
        List<String> onlineUsers = socketServers.stream()
                .map(client -> client.getUid())
                .collect(Collectors.toList());

        return onlineUsers;
    }


    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     *
     * @param message
     * @param uid     可以四username  消息发送者
     * @param listUid 可以四username  消息接收者
     */
    public synchronized void sendMessage(String message, String uid, List<String> listUid) throws IOException {
        // 超级管理员登录时，可以监听其他所有信息
        List<Client> listSuper = socketServers.stream().filter(item ->item.getUid().equals(SYS_ORIGINAL_USERNAME) && !uid.equals(SYS_ORIGINAL_USERNAME)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(listSuper)) {
            for (Client client : listSuper) {
                String msg="【"+uid+"】给【"+client.getUid()+"】发送消息："+message;
                if(client.getUid().equals(SYS_ORIGINAL_USERNAME)){
                    msg=message;
                }
                MsgDtoOut out = new MsgDtoOut();
                out.setType("2").setData(msg).setUid(client.getUid());
                client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
            }
        }



        if (CollectionUtils.isEmpty(listUid)) {
            for (Client client : socketServers) {
                if (!uid.equals(client.getUid())) {  // 不给自己发信息
                    MsgDtoOut out = new MsgDtoOut();
                    out.setType("2").setData(message).setUid(client.getUid());
                    client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
                    log.info("【{}】给【{}】发送消息 :【{}】", uid, client.getUid(), JSON.toJSONString(out));
                }
            }
        }

        List<Client> listNormal = socketServers.stream().filter(item -> listUid.stream().anyMatch(d -> d.equals(item.getUid()))).collect(Collectors.toList());
        for (Client client : listNormal) {
            if (!uid.equals(client.getUid())) {  // 不给自己发信息
                MsgDtoOut out = new MsgDtoOut();
                out.setType("2").setData(message).setUid(client.getUid());
                client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
                log.info("【{}】给【{}】发送消息 :【{}】", uid, client.getUid(), JSON.toJSONString(out));
            }
        }

    }


    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     * 可是时没有连接websocket的用户调用，uid并不一定存在
     *
     * @param message
     * @param uid
     * @param toUid
     */
    public synchronized void sendMessage(String message, String uid, String toUid) throws IOException {
        List<Client> list = socketServers.stream().filter(item -> item.getUid().equals(toUid)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            session.getBasicRemote().sendText("用户【" + toUid + "】不在线");
            log.info("用户【{}】不在线", toUid);
        }

        for (Client client : list) {
            if (session != client.getSession()) {// 不给自己发送信息
                MsgDtoOut out = new MsgDtoOut();
                out.setType("2").setData(message).setUid(client.getUid());
                client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
                log.info("用户【{}】发送消息给【{}】 :【{}】", uid, client.getUid(), JSON.toJSONString(out));

            }
        }
    }


    /**
     * 信息群发，我们要排除服务端自己不接收到推送信息
     * 所以我们在发送的时候将服务端排除掉
     *
     * @param message
     */
    public synchronized void sendAll(String message) throws IOException {
        //群发，不发送给服务端自己
        for (Client client : socketServers) {
            MsgDtoOut out = new MsgDtoOut();
            out.setType("2").setData(message).setUid(client.getUid());
            client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
        }

        log.info("推送给所有用户 :【{}】", message);
    }


    /**
     * 信息群发，我们要排除服务端自己不接收到推送信息
     * 所以我们在发送的时候将服务端排除掉
     * 可是时没有连接websocket的用户调用，uid并不一定存在
     *
     * @param message
     * @param senderUid 发送者的uid 可以为空（标识没有连接websocket的用户发送）
     */
    public synchronized void sendAll(String message, String senderUid) throws IOException {
        //群发，不发送给服务端自己
        List<Client>list=socketServers.stream().filter(item -> !item.getUid().equals(senderUid)).collect(Collectors.toList());
        for(Client client:list) {
            MsgDtoOut out = new MsgDtoOut();
            out.setType("2").setData(message).setUid(client.getUid());
            client.getSession().getBasicRemote().sendText(JSON.toJSONString(out));
        }
        log.info("推送给所有用户 :【{}】", message);
    }

    /**
     * 多个人发送给指定的几个用户
     *
     * @param message
     * @param listUid
     * @param uid  发送者可以为空
     */
    public synchronized void sendMany(String message, List<String> listUid,String uid) throws IOException {
        if(StringUtils.hasLength(uid)){
            sendMessage(message, uid,listUid);
        }
    }


}
