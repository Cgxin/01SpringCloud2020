package com.atguigu.springcloud.serivce;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author peove
 * @date 2021-11-10-17:16
 */
@Service
public class PaymentService {

    // ------------------- 服务降级 -------------------

    public String paymentInfo_OK (Integer id) {
        return "线程池: " + Thread.currentThread().getName() + ", paymentInfo_OK, id = " + id;
    }

    /*
    * 超时访问, 降级演示.
    * @HystrixProperty 中的 value的值 是超过5s了, 走 paymentInfo_TimeoutHandler()
    * 代码报错 和 超时 都会走 服务降级。  （代码报错如:         int a = 10 / 0; // java.lang.ArithmeticException: / by zero)
    * */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout (Integer id) {
        int timeNumber = 1000; // 大于@HystrixProperty 中的 value值 会 服务降级.
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + ", paymentInfo_Timeout, id = " + id + ", 耗时 " + timeNumber + "ms.";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + ", paymentInfo_TimeoutHandler, id = " + id + ", 请稍后再试.";
    }


    // ------------------- 服务熔断 -------------------
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
    commandProperties = { // 下面的意思就是 在 10s 内, 请求次数达到10次, 有60%以上的 失败率 就启动断路器. 然后后面正常了 会慢慢恢复.
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少后跳闸.
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + " 调用成功, 流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id不能为负数, 请稍后再试, id: " + id;
    }
}
