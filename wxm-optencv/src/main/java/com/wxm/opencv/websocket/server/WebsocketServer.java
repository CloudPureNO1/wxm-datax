package com.wxm.opencv.websocket.server;

import com.wxm.opencv.demo.TackPicture;
import com.wxm.opencv.demo.TackPictureWithWebsocket;
import com.wxm.opencv.websocket.dto.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
@ServerEndpoint(value = "/socketServer/{uid}")
@Component
public class WebsocketServer {

    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     **/
//    private static CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket封装的session,信息推送，就是通过它来信息推送
     */
    private Session session;

    /**
     *
     * 服务端的userName,因为用的是set，每个客户端的username必须不一样，否则会被覆盖。
     * 要想完成ui界面聊天的功能，服务端也需要作为客户端来接收后台推送用户发送的信息
     */
    private final static String SYS_USERNAME = "wxm";

    private final static String SYS_TAKE_PICTURE_WXM="Send(SYS_22-TAKE-PICTURE-11_WXM)";

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
    public void open(Session session, @PathParam(value = "uid") String uid) {
        this.session = session;
        if(!uid.equals(session.getId())){
            socketServers.add(new Client(uid, session));
            log.info("客户端:【{}】连接成功", uid);
        }{
            log.info("客户端:【{}】连接成功", uid);
        }
        try {
            // 打开摄像头
            new TackPictureWithWebsocket(uid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 收到客户端发送信息时触发
     * 我们将其推送给客户端(wxm)
     * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        if(message.startsWith(SYS_TAKE_PICTURE_WXM)){
            try {
                // 打开摄像头
                new TackPictureWithWebsocket(message.substring(message.indexOf("::")+2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            Client client = socketServers.stream().filter(cli -> cli.getSession() == session).collect(Collectors.toList()).get(0);
            sendMessage(client.getUid() + "<--" + message, SYS_USERNAME);
            log.info("客户端:【{}】发送信息:{}", client.getUid(), message);
        }
    }


    /**
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose() {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                log.info("客户端:【{}】断开连接", client.getUid());
                socketServers.remove(client);
            }
        });
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
                log.error("客户端:【{}】发生异常", client.getUid());
                error.printStackTrace();
            }
        });
    }

    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     *
     * @param message
     * @param userName
     */
    public synchronized static void sendMessage(String message, String userName) {

        socketServers.forEach(client -> {
            if (userName.equals(client.getUid())) {
                try {
                    client.getSession().getBasicRemote().sendText(message);
                    log.info("服务端推送给客户端 :【{}】", client.getUid(), message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public synchronized static int getOnlineNum() {
        return socketServers.stream().filter(client -> !client.getUid().equals(SYS_USERNAME))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获取在线用户名，前端界面需要用到
     *
     * @return
     */
    public synchronized static List<String> getOnlineUsers() {
        List<String> onlineUsers = socketServers.stream()
                .filter(client -> !client.getUid().equals(SYS_USERNAME))
                .map(client -> client.getUid())
                .collect(Collectors.toList());

        return onlineUsers;
    }

    /**
     * 信息群发，我们要排除服务端自己不接收到推送信息
     * 所以我们在发送的时候将服务端排除掉
     *
     * @param message
     */
    public synchronized static void sendAll(String message) {
        //群发，不能发送给服务端自己
        socketServers.stream().filter(cli -> cli.getUid() != SYS_USERNAME)
                .forEach(client -> {
                    try {
                        client.getSession().getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        log.info("服务端推送给所有客户端 :【{}】", message);
    }

    /**
     * 多个人发送给指定的几个用户
     *
     * @param message
     * @param persons
     */
    public synchronized static void SendMany(String message, List<String> persons) {
        for (String userName : persons) {
            sendMessage(message, userName);
        }
    }
}
