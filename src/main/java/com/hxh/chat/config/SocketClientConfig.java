package com.hxh.chat.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: H_xinghai
 * @Date: 2019/9/6 17:13
 * @Description: socketIOServer 配置类
 */
@Configuration
@Slf4j
public class SocketClientConfig {

//    nettySocketIO:
//    port: 8990
//    linkedCount: 200
//    allowRequest: true
//            # 协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间
//    upgradeTimeOut: 10000
//            #  60s内未接受到消息发送超时事件
//    pingTimeOut: 60000
//    pnigSpace: 25000
//            #设置交互内容长度
//    contextLength: 2071738
//            #设置每帧处理数据大小（防注入攻击）
//    payloadLength: 2071738

    @Value("${nettySocketIO.port}")
    private int port;

    @Value("${nettySocketIO.linkedCount}")
    private int linkedCount;

    @Value("${nettySocketIO.allowRequest}")
    private Boolean allowRequest;

    @Value("${nettySocketIO.upgradeTimeOut}")
    private Integer upgradeTimeOut;

    @Value("${nettySocketIO.pingTimeOut}")
    private Integer pingTimeOut;

    @Value("${nettySocketIO.pingSpace}")
    private Integer pingSpace;

    @Value("${nettySocketIO.contextLength}")
    private Integer contextLength;

    @Value("${nettySocketIO.payloadLength}")
    private Integer payloadLength;


    @Bean("socketIOServer")
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();

        configuration.setPort(port);

        SocketConfig socketConfig =  new SocketConfig();
        socketConfig.setReuseAddress(true);
        configuration.setSocketConfig(socketConfig);
        configuration.setWorkerThreads(linkedCount);
        configuration.setAllowCustomRequests(allowRequest);
        configuration.setUpgradeTimeout(upgradeTimeOut);
        configuration.setPingTimeout(pingTimeOut);
        configuration.setPingInterval(pingSpace);
        configuration.setMaxHttpContentLength(contextLength);
        configuration.setMaxFramePayloadLength(payloadLength);
        return  new SocketIOServer(configuration);
    }

    //开启socketIO注解
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer){
        return new SpringAnnotationScanner(socketIOServer);
    }
}
