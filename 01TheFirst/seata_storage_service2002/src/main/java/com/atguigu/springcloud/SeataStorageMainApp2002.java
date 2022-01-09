package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author peove
 * @date 2022-01-06-21:35
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据源的自动创建, 使用DataSourceProxyConfig
@EnableDiscoveryClient
@EnableFeignClients
public class SeataStorageMainApp2002 {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApp2002.class, args);
    }
}
