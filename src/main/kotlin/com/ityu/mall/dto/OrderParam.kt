package com.ityu.mall.dto

/**
 * 生成订单时传入的参数
 * Created by macro on 2018/8/30.
 */
class OrderParam {
    //收货地址id
    var memberReceiveAddressId: Long? = null
    //优惠券id
    var couponId: Long? = null
    //使用的积分数
    var useIntegration: Int? = null
    //支付方式
    var payType: Int? = null
}
