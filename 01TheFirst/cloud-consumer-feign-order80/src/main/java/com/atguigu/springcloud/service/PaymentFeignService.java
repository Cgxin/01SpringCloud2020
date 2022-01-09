package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    /*
    * getPaymentById() 方法被调用时候, 找 name = cloud-payment-service 的地址, 接口路径是 /payment/get/{id}
    * */
//    @GetMapping(value = "/payment/get/{id}")
//    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /*
    * name = cloud-payment-service 的服务地址（端口8001 和 8002的项目）, 接口路径是 /payment/getPaymentById
    * 需要添加 @RequestParam 用于纠正参数映射, 不然报错 405: FeignException$MethodNotAllowed: status 405 reading
    * */
    @GetMapping(value = "/payment/getPaymentById")
    CommonResult<Payment> getPaymentById(@RequestParam("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
