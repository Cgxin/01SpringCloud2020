package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.server.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-02-14:45
 */
@RestController
@Slf4j
@RequestMapping(value = "/consumer")
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/fallback")
//    @SentinelResource(value = "fallback") // 没有配置
//    @SentinelResource(value = "fallback", fallback = "handlerException") // fallback负责 运行异常.
    @SentinelResource(value = "fallback", fallback = "handlerException"
            , blockHandler = "handlerBlock" // 同时 存在 fallback 和 blockHandler, 走 blockHandler, blockHandler是入口，还没进入到程序.
            , exceptionsToIgnore = {IllegalArgumentException.class, NoSuchFieldException.class} // 忽略这2种异常, 不走 fallback 兜底方法.
            )
    public CommonResult<?> paymentInfo(Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL?id=" + id, CommonResult.class);

        if (id == 4) { // 注意 128
            throw new IllegalArgumentException("非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("该id没有对应记录");
        } else {

        }
        return result;
    }

    public CommonResult<?> handlerException(Long id, Throwable e) {
        return new CommonResult<>(444, "兜底异常handlerFallback, exception: " + e.getMessage(), new Payment(id, null));
    }

    public CommonResult<?> handlerBlock(Long id, BlockException e) {
        return new CommonResult<>(445, "配置异常handlerBlock, exception: " + e.getMessage(), new Payment(id, null));
    }


    // ---------------- openFeign ----------------
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/paymentSQL")
    public CommonResult<Payment> paymentSQL(Long id) {
        return paymentService.paymentSQL(id);
    }
}
