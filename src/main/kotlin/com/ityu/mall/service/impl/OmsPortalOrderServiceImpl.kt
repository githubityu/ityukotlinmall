package com.ityu.mall.service.impl


import com.ityu.mall.common.CommonResult
import com.ityu.mall.component.CancelOrderSender
import com.ityu.mall.dto.ConfirmOrderResult
import com.ityu.mall.dto.OrderParam

import com.ityu.mall.repo.OmsOrderSettingRepository
import com.ityu.mall.service.OmsCartItemService
import com.ityu.mall.service.OmsPortalOrderService
import com.ityu.mall.service.UmsMemberReceiveAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


/**
 * 前台订单管理Service
 * Created by macro on 2018/8/30.
 */
@Service
class OmsPortalOrderServiceImpl : OmsPortalOrderService {

    @Autowired
    private val cartItemService: OmsCartItemService? = null
    @Autowired
    private val memberReceiveAddressService: UmsMemberReceiveAddressService? = null
    @Autowired
    private val orderSettingMapper: OmsOrderSettingRepository? = null


    @Autowired
    private val cancelOrderSender: CancelOrderSender? = null

    @Value("\${rediskey.prefix.orderId}")
    private val REDIS_KEY_PREFIX_ORDER_ID: String? = null


    override fun generateConfirmOrder(): ConfirmOrderResult {
        return ConfirmOrderResult()
    }

    override fun generateOrder(orderParam: OrderParam): CommonResult<*> {
        return CommonResult.failed<Any>()
    }

    override fun paySuccess(orderId: Long?): CommonResult<*> {
        return CommonResult.failed<Any>()
    }

    override fun cancelTimeOutOrder(): CommonResult<*> {
        return CommonResult.failed<Any>()
    }

    override fun cancelOrder(orderId: Long?) {

    }

    override fun sendDelayMessageCancelOrder(orderId: Long?) {

        val omsOrderSetting = orderSettingMapper!!.findById(1L).get()
        val delayTimes = (omsOrderSetting.normalOrderOvertime!! * 60 * 1000).toLong()
        //发送延迟消息
        cancelOrderSender!!.sendMessage(orderId, delayTimes)
    }
}
