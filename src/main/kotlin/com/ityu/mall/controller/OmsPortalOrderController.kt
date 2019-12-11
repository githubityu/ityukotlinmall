package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.ConfirmOrderResult
import com.ityu.mall.dto.OrderParam
import com.ityu.mall.service.OmsPortalOrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 订单管理Controller
 * Created by macro on 2018/8/30.
 */
@RestController
@Api(tags = ["OmsPortalOrderController"], description = "订单管理")
@RequestMapping("/order")
class OmsPortalOrderController {
    @Autowired
    private val portalOrderService: OmsPortalOrderService? = null

    @ApiOperation("根据购物车信息生成确认单信息")
    @PostMapping(value = "/generateConfirmOrder")
    fun generateConfirmOrder(): CommonResult<ConfirmOrderResult> {
        val confirmOrderResult = portalOrderService!!.generateConfirmOrder()
        return CommonResult.success(confirmOrderResult)
    }

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping(value = "/generateOrder")
    fun generateOrder(@RequestBody orderParam: OrderParam): Any {
        return portalOrderService!!.generateOrder(orderParam)
    }

    @ApiOperation("支付成功的回调")
    @PostMapping(value = "/paySuccess")
    fun paySuccess(@RequestParam orderId: Long?): Any {
        return portalOrderService!!.paySuccess(orderId)
    }

    @ApiOperation("自动取消超时订单")
    @PostMapping(value = "/cancelTimeOutOrder")
    fun cancelTimeOutOrder(): Any {
        return portalOrderService!!.cancelTimeOutOrder()
    }

    @ApiOperation("取消单个超时订单")
    @PostMapping(value = "/cancelOrder")
    fun cancelOrder(orderId: Long?): CommonResult<*> {
        portalOrderService!!.sendDelayMessageCancelOrder(orderId)
        return CommonResult.success<Any>(null)
    }
}
