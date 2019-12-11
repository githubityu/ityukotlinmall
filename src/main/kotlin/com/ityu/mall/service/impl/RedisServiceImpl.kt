package com.ityu.mall.service.impl


import com.ityu.mall.service.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

import java.util.concurrent.TimeUnit

/**
 * redis操作Service的实现类
 * Created by macro on 2018/8/7.
 */
@Service
class RedisServiceImpl : RedisService {
    @Autowired
    private val stringRedisTemplate: StringRedisTemplate? = null

    override fun set(key: String, value: String) {
        stringRedisTemplate!!.opsForValue().set(key, value)
    }
    override fun get(key: String): String? {
        return stringRedisTemplate!!.opsForValue().get(key)
    }

    override fun expire(key: String, expire: Long): Boolean {
        return stringRedisTemplate!!.expire(key, expire, TimeUnit.SECONDS)
    }

    override fun remove(key: String) {
        stringRedisTemplate!!.delete(key)
    }

    override fun increment(key: String, delta: Long): Long? {
        return stringRedisTemplate!!.opsForValue().increment(key, delta)
    }
}
