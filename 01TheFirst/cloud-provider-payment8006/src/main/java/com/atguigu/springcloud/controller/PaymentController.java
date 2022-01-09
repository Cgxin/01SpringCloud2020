package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author peove
 * @date 2021-11-10-17:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    /*
    * 安装 consul 打开下面网址, 可以监控, 类似与 localhost:7001 （eureka7001.com:7001）
    * localhost:8500/ui/dc1/services
    * */
    @RequestMapping(value = "/consul")
    public String paymentConsul() {
        return "spring cloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
