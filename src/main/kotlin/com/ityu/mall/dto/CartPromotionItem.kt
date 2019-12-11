package com.ityu.mall.dto


import com.ityu.mall.model.OmsCartItem

import java.math.BigDecimal

/**
 * Created by macro on 2018/8/27.
 * 购物车中促销信息的封装
 */
class CartPromotionItem : OmsCartItem() {
    //促销活动信息
    var promotionMessage: String? = null
    //促销活动减去的金额，针对每个商品
    var reduceAmount: BigDecimal? = null
    //商品的真实库存（剩余库存-锁定库存）
    var realStock: Int? = null
    //购买商品赠送积分
    var integration: Int? = null
    //购买商品赠送成长值
    var growth: Int? = null
}
