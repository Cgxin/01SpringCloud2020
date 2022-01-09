package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author peove
 * @date 2021-12-13-10:58
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 没有这个注解, 会不认识 微服务名.
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
