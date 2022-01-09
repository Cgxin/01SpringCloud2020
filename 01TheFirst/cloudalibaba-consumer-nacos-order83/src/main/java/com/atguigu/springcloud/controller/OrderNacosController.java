package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-02-14:45
 */
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment")
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/nacos")
    public String paymentInfo(Integer id) {
        return restTemplate.getForObject(serverUrl + "/payment/nacos?id=" + id, String.class);
    }
}
