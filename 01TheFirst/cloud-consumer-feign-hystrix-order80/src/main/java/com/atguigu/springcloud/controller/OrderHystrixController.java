package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2021-12-27-17:56
 */
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment/hystrix")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
    })// Hystrix 降级服务 全局 兜底方法.
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/ok")
    public String paymentInfo_OK(Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping(value = "/timeout")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand // 用全局的 需要加这个注解, 属性 自己写的 就用自己的.
    public String paymentInfo_Timeout(Integer id) {
        long a = System.currentTimeMillis();
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        System.err.println("耗时: " + (System.currentTimeMillis() - a) + "ms.");
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "我是消费者80, 对方支付系统繁忙, 请稍后再试.";
    }

    /*
    * 下面是全局 fallback方法
    * */
    public String payment_Global_FallbackMethod() {
        return "Global是异常处理信息, 请稍后再试.";
    }
}
