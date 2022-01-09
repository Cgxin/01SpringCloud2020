package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author peove
 * @date 2021-11-10-16:20
 */
@SpringBootApplication
@EnableDiscoveryClient // 该注解用于向consul或者 zookeeper作为注册中心时注册服务
public class OrderZkMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderZkMain80.class, args);
    }
}
