package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2021-11-10-17:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/payment/hystrix")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/ok")
    public String paymentInfo_OK(Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        System.err.println("result = " + result); // TODO=== delete
        return result;
    }

    @GetMapping(value = "/timeout")
    public String paymentInfo_Timeout(Integer id) {
        String result = paymentService.paymentInfo_Timeout(id);
        System.err.println("result = " + result); // TODO=== delete
        return result;
    }

    // 服务熔断
    @GetMapping(value = "/circuit")
    public String paymentCircuitBreaker(Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        System.err.println("result = " + result); // TODO===delete
        return result;
    }
}
