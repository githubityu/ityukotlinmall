package com.ityu.mall.dto

import lombok.Getter
import lombok.Setter

import java.util.Date

/**
 * 首页当前秒杀场次信息
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
class HomeFlashPromotion {
    var startTime: Date? = null
        set(startTime) {
            field = startTime
        }
    var endTime: Date? = null
        set(endTime) {
            field = endTime
        }
    var nextStartTime: Date? = null
        set(nextStartTime) {
            field = nextStartTime
        }
    var nextEndTime: Date? = null
        set(nextEndTime) {
            field = nextEndTime
        }
    //属于该秒杀活动的商品
    var productList: List<FlashPromotionProduct>? = null
        set(productList) {
            field = productList
        }
}
