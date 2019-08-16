package com.strady.websocketchat.server;

import com.alibaba.fastjson.JSON;
import com.strady.websocketchat.domain.Message;
import com.strady.websocketchat.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: strady
 * @Date: 2019/7/6
 * @Time: 11:18
 * @Description: 一对一聊天
 */

@Slf4j
@Component
@ServerEndpoint("/chat/{uid}")
public class WebSocketChatServer {

    /**
     * 使用线程安全的Map存储所有webSocket对象
     */
    private static Map<String, WebSocketChatServer> websockets = new ConcurrentHashMap<>();

    /**
     * 使用线程安全的Map存储在线用户
     */
    private static Map<String, User> users = new ConcurrentHashMap<>();

    private Session session;

    //在线人数
    private static int onlineCount = 0;

    /**
     * 获取当前在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人数加一
     */
    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    /**
     * 在线人数减一
     */
    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

    /**
     * 开启连接
     *
     * @param uid
     * @param session
     * @throws Exception
     */
    @OnOpen
    public void onOpen(@PathParam("uid") String uid, Session session) throws Exception {
        this.session = session;
        //判断在线用户中是否有当前用户
        if (!users.containsKey(uid)) {
            /**
             * TODO 查找用户是否存在，不存在的话就是跳出
             */
            //用户存在的话就存入在线用户列表
//            users.put(uid, null);
        }
        //将当前webSocket存入列表
        websockets.put(uid, this);
        addOnlineCount();
        log.info("用户：" + uid + " 加入连接，在线人数" + onlineCount);
    }

    /**
     * 发送消息
     *
     * @param jsonStr
     * @throws Exception
     */
    @OnMessage
    public void onMessage(String jsonStr) throws Exception {
        Message message = JSON.parseObject(jsonStr, Message.class);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        message.setTime(time);
        message.setType(Message.STATUS.SPEAK);
        message.setUsername(message.getUid());
        message.setOnlineCount(onlineCount);
        message.toString();
        websockets.get(message.getUid()).session.getBasicRemote().sendText(message.toJsonStr());
        log.info("用户id：" + message.getUid() + " 发送：" + message.getMsg());
    }

    /**
     * 发生异常，打印日志
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 关闭连接
     *
     * @param uid
     * @throws Exception
     */
    @OnClose
    public void onClose(@PathParam("uid") String uid) throws Exception {
        //将用户从在线用户中移除
        users.remove(uid);
        //将websocket移除
        websockets.remove(uid);
        //在线人数减一
        subOnlineCount();
        log.info("用户：" + uid + " 关闭连接，在线人数" + onlineCount);
    }
}
