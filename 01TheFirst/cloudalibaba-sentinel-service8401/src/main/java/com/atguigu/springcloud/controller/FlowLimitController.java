package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peove
 * @date 2022-01-02-14:09
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA()  {
        System.out.println(" AAAA "); // TODO===delete
        return "-----test A";
    }

    @GetMapping(value = "/testB")
    public String testB() {
        System.out.println(" BBBB "); // TODO===delete
        log.info(Thread.currentThread().getName() + ", testB.");
        return "-----test B";
    }

    @GetMapping(value = "/testD")
    public String testD() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + ", testD.");
        return "-----test D";
    }

    @GetMapping(value = "/testE")
    public String testE() {
        log.info("异常比例");
        int a = 10 / 0;
        return "异常比例";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey") // 随便起，要保证唯一, 一般和 地址一样.
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey";
    }

    // 返回结果 只能 String 类型.
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        CommonResult<String> result = new CommonResult<>();
        result.setData("---- deal_testHotKey");
        result.setCode(555);
        result.setMessage("please wait.");
        return JSON.toJSONString(result);
//        return "---- deal_testHotKey"; // sentinel默认提示 blocked by sentinel ....
    }
}
