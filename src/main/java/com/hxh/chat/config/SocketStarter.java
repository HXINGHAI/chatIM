package com.hxh.chat.config;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: H_xinghai
 * @Date: 2019/9/9 13:46
 * @Description: 实时通信的启动类 在Application执行完之后开始执行这个
 */
@Slf4j
@Component
@Order(1)
public class SocketStarter implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    @Autowired
    public SocketStarter(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void run(String...args) throws Exception{
        log.info("即时通信开始启动。。。");
        socketIOServer.start();
    }
}
