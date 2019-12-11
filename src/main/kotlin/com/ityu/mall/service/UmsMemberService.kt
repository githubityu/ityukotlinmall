package com.ityu.mall.service


import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.UmsMember
import org.springframework.transaction.annotation.Transactional

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
interface UmsMemberService {

    /**
     * 获取当前登录会员
     */
    fun getCurrentMember(): UmsMember

    /**
     * 根据用户名获取会员
     */
    fun getByUsername(username: String): UmsMember?

    /**
     * 根据会员编号获取会员
     */
    fun getById(id: Long?): UmsMember

    /**
     * 用户注册
     */
    @Transactional
    fun register(username: String, password: String, telephone: String, authCode: String): CommonResult<Any>

    /**
     * 生成验证码
     */
    fun generateAuthCode(telephone: String): CommonResult<Any>

    /**
     * 修改密码
     */
    @Transactional
    fun updatePassword(telephone: String, password: String, authCode: String): CommonResult<Any>

    /**
     * 根据会员id修改会员积分
     */
    fun updateIntegration(id: Long?, integration: Int?)
}
