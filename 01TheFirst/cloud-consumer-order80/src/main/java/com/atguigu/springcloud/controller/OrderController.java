package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author peove
 * @date 2021-12-13-10:52
 */
@Slf4j
@RestController
@RequestMapping(value = "/consumer/payment")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; // 单机版
    public static final String PAYMENT_URL = "http://cloud-payment-service"; // 集群 写 微服务的名称 application.yml 里的  spring.application.name 的.

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/create")
    public CommonResult<?> create(Payment payment) {
        log.info("payment = {}", payment);

        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        System.err.println("entity = " + entity); // TODO=== delete
        System.err.println("entity.getStatusCode() = " + entity.getStatusCode()); // TODO=== delete

        // postForObject: Post请求, 参数 @RequestBody 接受.
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/get")
    public CommonResult<?> getPayment(Long id) {
        // getForObject: 普通的get请求
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById?id=" + id, CommonResult.class);
    }

    /*
    * @PathVariable 注解用法  http://localhost/consumer/payment/getForEntity/13
    * */
    @GetMapping("/getForEntity/{id}")
    public CommonResult<?> getPayment2(@PathVariable("id") Long id) {

        /*
        * getForEntity 返回对象是 ResponseEntity 的, 包含了 响应头、状态码、响应体. 等其他信息。
        * getForObject 返回的是 响应体中数据转化成的 json对象.
        * */
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById?id=" + id, CommonResult.class);
        System.err.println("entity = " + entity); // TODO == delete
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();
        System.out.println("uri = " + uri); // TODO== delete
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }


    /*
     * zipkin
     * */
    @GetMapping(value = "/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001/payment/zipkin/", String.class);
        return result;
    }
}
