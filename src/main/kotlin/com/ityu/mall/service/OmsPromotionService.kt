package com.ityu.mall.service


import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.model.OmsCartItem

/**
 * Created by macro on 2018/8/27.
 * 促销管理Service
 */
interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    fun calcCartPromotion(cartItemList: List<OmsCartItem>): List<CartPromotionItem>
}
