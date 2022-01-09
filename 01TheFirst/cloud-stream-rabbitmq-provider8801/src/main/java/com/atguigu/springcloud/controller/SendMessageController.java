package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-01-22:24
 */
@RestController
@Slf4j
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    /*
    * 1. 访问这个请求, 去 localhost:15672  （rabbitmq）里可以看到 Exchanges 里 名字为 studyExchange
    * 2. 创建 8802 消费者,  这里发送消息后, 8802会接收到
    * */
    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return iMessageProvider.send();
    }
}
