package com.ityu.mall.dto


import com.ityu.mall.model.UmsIntegrationConsumeSetting
import com.ityu.mall.model.UmsMemberReceiveAddress

import java.math.BigDecimal

/**
 * 确认单信息封装
 * Created by macro on 2018/8/30.
 */
class ConfirmOrderResult {
    //包含优惠信息的购物车信息
    var cartPromotionItemList: List<CartPromotionItem>? = null
    //用户收货地址列表
    var memberReceiveAddressList: List<UmsMemberReceiveAddress>? = null
    //用户可用优惠券列表
    var couponHistoryDetailList: List<SmsCouponHistoryDetail>? = null
    //积分使用规则
    var integrationConsumeSetting: UmsIntegrationConsumeSetting? = null
    //会员持有的积分
    var memberIntegration: Int? = null
    //计算的金额
    var calcAmount: CalcAmount? = null

    class CalcAmount {
        //订单商品总金额
        var totalAmount: BigDecimal? = null
        //运费
        var freightAmount: BigDecimal? = null
        //活动优惠
        var promotionAmount: BigDecimal? = null
        //应付金额
        var payAmount: BigDecimal? = null
    }
}
