package com.ityu.mall.service


import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.ConfirmOrderResult
import com.ityu.mall.dto.OrderParam
import org.springframework.transaction.annotation.Transactional

/**
 * 前台订单管理Service
 * Created by macro on 2018/8/30.
 */
interface OmsPortalOrderService {
    /**
     * 根据用户购物车信息生成确认单信息
     */
    fun generateConfirmOrder(): ConfirmOrderResult

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    fun generateOrder(orderParam: OrderParam): CommonResult<*>

    /**
     * 支付成功后的回调
     */
    @Transactional
    fun paySuccess(orderId: Long?): CommonResult<*>

    /**
     * 自动取消超时订单
     */
    @Transactional
    fun cancelTimeOutOrder(): CommonResult<*>

    /**
     * 取消单个超时订单
     */
    @Transactional
    fun cancelOrder(orderId: Long?)

    /**
     * 发送延迟消息取消订单
     */
    fun sendDelayMessageCancelOrder(orderId: Long?)
}
