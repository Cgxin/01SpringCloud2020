package com.atguigu.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peove
 * @date 2021-12-27-13:54
 */
@Configuration
public class MySelfRule {

    /*
    * 这个不能放到 启动类所在的包 及其 子包下, 不然 会被所有的ribbon 共享, 达不到 定制的 作用.
    * */
    @Bean
    public IRule myRule() {
//        new RetryRule(); //
//        new WeightedResponseTimeRule(); // 权重
//        new BestAvailableRule();
//        new AvailabilityFilteringRule();
//        new ZoneAvoidanceRule();

        return new RandomRule(); // 定义为 随机.  默认是 轮询.
    }
}
