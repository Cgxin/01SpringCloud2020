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

    // applicationContext.xml <bean id="" class="">
    @Bean
    @LoadBalanced /* 负载均衡注解, CLOUD-PAYMENT-SERVICE 这块.  注销 使用自己的. */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
