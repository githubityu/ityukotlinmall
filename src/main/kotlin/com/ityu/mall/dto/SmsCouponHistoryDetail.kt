package com.ityu.mall.dto


import com.ityu.mall.model.SmsCoupon
import com.ityu.mall.model.SmsCouponHistory
import com.ityu.mall.model.SmsCouponProductCategoryRelation
import com.ityu.mall.model.SmsCouponProductRelation

/**
 * 优惠券领取历史详情封装
 * Created by macro on 2018/8/29.
 */
class SmsCouponHistoryDetail : SmsCouponHistory() {
    //相关优惠券信息
    var coupon: SmsCoupon? = null
    //优惠券关联商品
    var productRelationList: List<SmsCouponProductRelation>? = null
    //优惠券关联商品分类
    var categoryRelationList: List<SmsCouponProductCategoryRelation>? = null
}
