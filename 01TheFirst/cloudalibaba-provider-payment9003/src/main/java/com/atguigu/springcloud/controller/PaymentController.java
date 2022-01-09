package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author peove
 * @date 2022-01-02-14:09
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a16f25-33f6-4610-aa25-92ec31ba0b22"));
        hashMap.put(2L, new Payment(2L, "bba1ca53-4227-4102-a983-222529f5d9a6"));
        hashMap.put(3L, new Payment(3L, "6ua18b16-9432-46df-bd1c-4afb9d893069"));
    }

    @GetMapping(value = "/paymentSQL")
    public CommonResult<?> getPayment(Long id) {
        CommonResult<Payment> result = new CommonResult<>();
        result.setCode(200);
        result.setMessage("from mysql, serverport: " + serverPort);
        result.setData(hashMap.get(id));
        return result;
    }
}
