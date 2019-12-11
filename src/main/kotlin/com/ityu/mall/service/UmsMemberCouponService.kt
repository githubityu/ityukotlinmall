package com.ityu.mall.service

import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.dto.SmsCouponHistoryDetail
import com.ityu.mall.model.SmsCouponHistory
import org.springframework.transaction.annotation.Transactional

/**
 * 用户优惠券管理Service
 * Created by macro on 2018/8/29.
 */
interface UmsMemberCouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    fun add(couponId: Long?,memId: Long?): CommonResult<*>?

    /**
     * 获取优惠券列表
     * @param useStatus 优惠券的使用状态
     */
    fun list(useStatus: Int?,memId: Long): List<SmsCouponHistory?>?

    /**
     * 根据购物车信息获取可用优惠券
     */
    fun listCart(cartItemList: List<CartPromotionItem>?, type: Int?,memId: Long): List<SmsCouponHistoryDetail>?
}