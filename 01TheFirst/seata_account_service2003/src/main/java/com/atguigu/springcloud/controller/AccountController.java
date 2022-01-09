package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.AccountSelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-06-21:24
 */
@RestController
@Slf4j
@RequestMapping(value = "/account")
public class AccountController {

    @Resource
    private AccountSelfService accountSelfService;

    @PostMapping(value = "/decrease")
    public CommonResult<?> decrease(Long userId, Integer money) {
        accountSelfService.decrease(userId, money);
        return new CommonResult<>(200, "减余额成功");
    }
}
