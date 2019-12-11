package com.ityu.mall.service

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 * Created by macro on 2018/8/7.
 */
interface RedisService {
    /**
     * 存储数据
     */
    operator fun set(key: String, value: String)

    /**
     * 获取数据
     */
    operator fun get(key: String): String?

    /**
     * 设置超期时间
     */
    fun expire(key: String, expire: Long): Boolean

    /**
     * 删除数据
     */
    fun remove(key: String)

    /**
     * 自增操作
     * @param delta 自增步长
     */
    fun increment(key: String, delta: Long): Long?

}
