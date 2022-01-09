package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.service.AccountSelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-06-21:11
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountSelfService {

    @Resource
    private AccountDao storageDao;

    @Override
    public void decrease(Long userId, Integer money) {
        log.info(" ---- AccountServiceImpl 减余额 start");

        // 模拟超时异常
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storageDao.decrease(userId, money);
        log.info(" ---- AccountServiceImpl 减余额 end");
    }
}
