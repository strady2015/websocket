package com.strady.websocketchat.domain;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: strady
 * @Date: 2019/7/5
 * @Time: 8:48
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public enum STATUS {
        //登录
        ENTER,
        //聊天
        SPEAK,
        //退出
        QUIT
    }

    /**
     * 消息类型
     */
    private STATUS type;

    /**
     * 发送人
     */
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 消息
     */
    private String msg;

    /**
     * 发送时间
     */
    private String time;

    /**
     * 在线人数
     */
    private int onlineCount;

    public static String jsonStr(STATUS type, String uid, String username, String msg, String time, int onlineTotal) {
        return JSON.toJSONString(new Message(type, uid, username, msg, time, onlineTotal));
    }

    public String toJsonStr() {
        return JSON.toJSONString(this);
    }
}
