package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author peove
 * @date 2021-11-11-12:04
 */
@SpringBootApplication
@EnableEurekaClient
// 负载均衡使用 自定义的, 不用默认的轮询.  name 必须和  8001 8002 配置里的 spring.application.name 保持一致, 大小写敏感.
@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
