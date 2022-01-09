package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2021-12-27-17:56
 */
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/get")
    public CommonResult<Payment> getPaymentById(Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon, 客户端默认等待1s.
        return paymentFeignService.paymentFeignTimeout();
    }
}
