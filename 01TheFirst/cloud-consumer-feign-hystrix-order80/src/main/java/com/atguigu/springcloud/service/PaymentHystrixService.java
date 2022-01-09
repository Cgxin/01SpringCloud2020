package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.PaymentHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cloud-payment-hystrix-service", fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok")
    String paymentInfo_OK(@RequestParam("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout")
    String paymentInfo_Timeout(@RequestParam("id") Integer id);
}
