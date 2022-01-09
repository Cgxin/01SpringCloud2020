package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2021-11-10-17:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/consumer")
public class OrderConsulController {

    public static final String INVOKE_URL = "http://cloud-provider-paymentConsul";  // 8006 yml 的 spring.application.name

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }
}
