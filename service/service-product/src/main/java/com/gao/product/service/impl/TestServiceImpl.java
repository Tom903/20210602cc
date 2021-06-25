package com.gao.product.service.impl;

import com.gao.common.constant.RedisConst;
import com.gao.model.product.SkuInfo;
import com.gao.product.service.TestService;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;


    @Override
    public  void testLock() {
        String s = UUID.randomUUID().toString();
        String skuId = "12";
        String lockey = "lock" + skuId;
        // 1. 从redis中获取锁,setnx
//        Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockkey, s);
        // 锁的是每个商品
        RLock lock = redissonClient.getLock(lockey);
        lock.tryLock();
        String value = (String)this.redisTemplate.opsForValue().get("num");
        if (StringUtils.isBlank(value)){
            return;
        }
        // 将value 变为int
        int num = Integer.parseInt(value);
        // 将num +1 放入缓存
        redisTemplate.opsForValue().set("num",String.valueOf(++num));
        lock.unlock();
        /*if (lock) {
            // 查询redis中的num值
            String value = (String)this.redisTemplate.opsForValue().get("num");
            // 没有该值return
            if (StringUtils.isBlank(value)){
                return ;
            }
            // 有值就转成成int
            int num = Integer.parseInt(value);
            // 把redis中的num值+1
            this.redisTemplate.opsForValue().set("num", String.valueOf(++num));
            // 2. 释放锁 del
            if (s.equals(redisTemplate.opsForValue().get("lock"))) {
                redisTemplate.delete("lock");
            }

        } else {
            // 3. 每隔1秒钟回调一次，再次尝试获取锁
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public String readLock() {
        RReadWriteLock readwriteLuck = redissonClient.getReadWriteLock("readwriteLuck");
        RLock lock = readwriteLuck.readLock();
        lock.lock(10, TimeUnit.SECONDS);
        String msg = (String) redisTemplate.opsForValue().get("msg");
        return msg;
    }

    @Override
    public String writeLock() {
        RReadWriteLock readwriteluck = redissonClient.getReadWriteLock("readwriteluck");
        RLock lock = readwriteluck.writeLock();
        lock.lock(10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("msg", UUID.randomUUID().toString());
        return "";
    }
}
