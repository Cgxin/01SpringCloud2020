package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2021-11-10-17:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public CommonResult<?> create(@RequestBody Payment payment) {

        CommonResult<Integer> result = new CommonResult<>();

        log.info("payment = " + payment);
        int code = paymentService.create(payment);
        log.info("code = " + code);

        result.setCode(code > 0 ? 200 : 500);
        result.setMessage(code > 0 ? "success, port: " + serverPort : "failed");
        result.setData(code);
        return result;
    }

    @GetMapping(value = "/getPaymentById")
    public CommonResult<?> getPaymentById(Long id) {

        CommonResult<Payment> result = new CommonResult<>();

        Payment payment = paymentService.getPaymentById(id);
        log.info("payment = " + payment);

        result.setCode(payment != null ? 200 : 500);
        result.setMessage(payment != null ? "success, port: " + serverPort : "failed");
        result.setData(payment);
        return result;
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<?> get(@PathVariable("id") Long id) {

        CommonResult<Payment> result = new CommonResult<>();

        Payment payment = paymentService.getPaymentById(id);
        log.info("payment = " + payment);

        result.setCode(payment != null ? 200 : 500);
        result.setMessage(payment != null ? "success, port: " + serverPort : "failed");
        result.setData(payment);
        return result;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
