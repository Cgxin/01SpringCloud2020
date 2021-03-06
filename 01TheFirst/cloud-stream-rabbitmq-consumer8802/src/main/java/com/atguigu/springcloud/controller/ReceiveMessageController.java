package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author peove
 * @date 2022-01-02-9:26
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageController {

    @Value("${server.port}")
    private String serverPort;

    /*
    * 接收 StreamMQMain8801 [devtools] :8801/  发送的消息
    * */
    @StreamListener(Sink.INPUT)  // 监听队列, 用于消费者的队列的消息接收
    public void input(Message<String> message) {
        System.out.println("port: " + serverPort + "消费者1号, 接收到的消息: " + message.getPayload());
    }
}
