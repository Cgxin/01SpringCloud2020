package com.atguigu.springcloud.server.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.server.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @author peove
 * @date 2022-01-05-20:13
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级, PaymentServiceImpl");
    }
}
