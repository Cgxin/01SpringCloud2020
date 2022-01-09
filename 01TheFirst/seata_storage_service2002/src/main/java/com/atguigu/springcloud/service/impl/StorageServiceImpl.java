package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.StorageDao;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author peove
 * @date 2022-01-06-21:11
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info(" ---- StorageServiceImpl 减库存 start");
        storageDao.decrease(productId, count);
        log.info(" ---- StorageServiceImpl 减库存 end");
    }
}
