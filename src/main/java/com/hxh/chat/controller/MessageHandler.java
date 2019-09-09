package com.hxh.chat.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.hxh.chat.entity.UserMessage;
import com.hxh.chat.enums.UserState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: H_xinghai
 * @Date: 2019/9/9 13:55
 * @Description: 消息处理类
 */
@Slf4j
@Component
public class MessageHandler {


    private SocketIOServer socketIOServer;

    //定义用户保存相关人员(暂时容纳50人)

    private Map<String, UUID> userMap = new ConcurrentHashMap<>(50);

    @Autowired
    public MessageHandler(SocketIOServer socketIOServer){
        this.socketIOServer = socketIOServer;
    }
    //添加消息处理事件

    //连接处理事件

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient){
        if (null != socketIOClient){
            String userName = socketIOClient.getHandshakeData().getSingleUrlParam("name");
            if (StringUtils.isNotBlank(userName)){
                log.info("{}开始了长连接。。。",userName);
                userMap.put(userName,socketIOClient.getSessionId());
                //通知全局某用户已经连接
                broadcast(socketIOClient,null,new UserMessage(userName,UserState.ONLINE.getValue()));
            }
        } else {
            log.info("客户端为空~！");
        }
    }
    //断开事件处理

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient){
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("name");
        log.info("{}断开连接,sessionId 为{}",userName,socketIOClient.getSessionId().toString());
        userMap.remove(userName);
        //通知全局 某用户已经离开
        broadcast(socketIOClient,null,new UserMessage(userName,UserState.OFFLINE.getValue()));
    }

    //消息发送事件(广播事件)

    @OnEvent(value = "broadcast")
    private void broadcast(SocketIOClient socketIOClient, AckRequest ackRequest, UserMessage userMessage){
        log.info("开始向全局的用户广播你的消息内容");
        userMap.forEach((k,v) ->{
            if (null != v){
                socketIOServer.getClient(v).sendEvent("message",userMessage);
            }
        });
    }
}
