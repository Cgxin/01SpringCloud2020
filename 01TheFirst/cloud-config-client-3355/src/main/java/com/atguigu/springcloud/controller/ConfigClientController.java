package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peove
 * @date 2021-12-31-18:05
 */
@RestController
@RefreshScope // 动态刷新配置.
public class ConfigClientController {

    // GitHub 上 config-dev.yml 配置文件里面的  config.info           当然 3344 关联不了 github, 这里取不到值, 跑不起来.
    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
