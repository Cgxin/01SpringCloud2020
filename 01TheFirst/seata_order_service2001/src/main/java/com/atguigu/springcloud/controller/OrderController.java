package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entity.Order;
import com.atguigu.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-06-21:24
 */
@RestController
@Slf4j
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/create")
    public CommonResult<?> create(Order order) {
        orderService.create(order);
        return new CommonResult<>(200, "创建订单成功");
    }
}
