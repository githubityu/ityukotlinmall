package com.ityu.mall.dto


import com.ityu.mall.model.PmsProduct
import lombok.Getter
import lombok.Setter

import java.math.BigDecimal

/**
 * 秒杀信息和商品对象封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
class FlashPromotionProduct : PmsProduct() {
    var flashPromotionPrice: BigDecimal? = null
        set(flashPromotionPrice) {
            field = flashPromotionPrice
        }
    var flashPromotionCount: Int? = null
        set(flashPromotionCount) {
            field = flashPromotionCount
        }
    var flashPromotionLimit: Int? = null
        set(flashPromotionLimit) {
            field = flashPromotionLimit
        }
}
