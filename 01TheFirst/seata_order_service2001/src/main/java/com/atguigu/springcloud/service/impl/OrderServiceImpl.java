package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.entity.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-06-21:11
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "suibianButweiyi", rollbackFor = Exception.class)
//    @Transactional // 只 回滚了 当前项目数据库的数据， 其他的 数据库 未回滚。
    public void create(Order order) {
        System.err.println("order = " + order); // TODO=== delete
        log.info(" ---- 开始新建订单");
        orderDao.create(order);

        log.info(" ---- 订单微服务开始调用库存, 做扣减 start");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info(" ---- 订单微服务开始调用库存, 做扣减 end");

        log.info(" ---- 订单微服务开始调用账户, 做扣钱 start");
        accountService.decrease(order.getUserId(), Integer.parseInt(order.getMoney().toString())); // 数据库字段类型建的不一样了... 不要在意细节了.
        log.info(" ---- 订单微服务开始调用账户, 做扣钱 end");

        // 修改订单状态
        log.info(" ---- 修改订单状态 start");
        orderDao.update(order.getUserId(), 0);
        log.info(" ---- 修改订单状态 end");
    }
}
