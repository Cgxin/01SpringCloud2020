package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peove
 * @date 2021-12-30-11:01
 */
@Configuration
public class GateWayConfig {

    /*
    * 路由的两种配置方式,  一种是下面, 另一种是 配置文件.
    * */
    @Bean
    public RouteLocator coustomRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // http://localhost:9527/board  即可跳转 https://top.baidu.com/board
        routes.route("path_route_atguigu",
                r -> r.path("/board")
                        .uri("https://top.baidu.com/board")).build();
        return routes.build();
    }
}
