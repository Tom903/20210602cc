package com.gao.common.cache;


import com.alibaba.fastjson.JSON;
import com.gao.common.constant.RedisConst;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import lombok.Singular;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Aspect
public class GaoCacheAspec {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @SneakyThrows
    @Around("@annotation(com.gao.common.cache.GaoCache)")
    public Object cacheAroundAdvice(ProceedingJoinPoint joinPoint) {
        Object o = new Object();
        MethodSignature signature  = (MethodSignature) joinPoint.getSignature();
        GaoCache gaoCache = signature.getMethod().getAnnotation(GaoCache.class);
        String prefix = gaoCache.prefix();//获取注解的前缀
        Object[] args = joinPoint.getArgs();//获取方法传入的参数
        String key = prefix + Arrays.asList(args).toString();//前缀+参数 = 缓存的key
        try {
            if (o == null) {
                //采用前缀+lock作为不同的lock
                String lockKey = prefix + "lock";
                RLock lock = redissonClient.getLock(lockKey);
                boolean b = lock.tryLock(RedisConst.SKULOCK_EXPIRE_PX1, RedisConst.SKULOCK_EXPIRE_PX2, TimeUnit.SECONDS);
                if (b) {
                    try {
                        //  表示执行方法体 getSkuInfoDB(skuId);
                        o = joinPoint.proceed(joinPoint.getArgs());
                        //  判断object 是否为空
                        if (o == null) {
                            Object o1 = new Object();
                            redisTemplate.opsForValue().set(key, JSON.toJSONString(o1), RedisConst.SKUKEY_TEMPORARY_TIMEOUT, TimeUnit.SECONDS);
                            return o1;
                        }
                        redisTemplate.opsForValue().set(key, JSON.toJSONString(o), RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                        return o;
                    } finally {
                        lock.unlock();
                    }
                } else {
                    Thread.sleep(1000);
                    return cacheAroundAdvice(joinPoint);
                }
            } else {
                return o;
            }
        } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
    //  如果出现问题数据库兜底
    return joinPoint.proceed(joinPoint.getArgs());
}

    /**
     *  表示从缓存中获取数据
     * @param key 缓存的key
     * @param signature 获取方法的返回值类型
     * @return
     */
    private Object cacheHit(String key, MethodSignature signature) {
        //  通过key 来获取缓存的数据
        String strJson = (String) redisTemplate.opsForValue().get(key);
        //  表示从缓存中获取到了数据
        if (!StringUtils.isEmpty(strJson)){
            //  字符串存储的数据是什么?   就是方法的返回值类型
            Class returnType = signature.getReturnType();
            //  将字符串变为当前的返回值类型
            return JSON.parseObject(strJson,returnType);
        }
        return null;
    }
}
