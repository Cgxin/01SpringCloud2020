package com.atguigu.springcloud.server;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.server.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentServiceImpl.class) // value 微服务名称.
public interface PaymentService {

    @GetMapping(value = "/paymentSQL")
    CommonResult<Payment> paymentSQL(@RequestParam("id") Long id);
}
