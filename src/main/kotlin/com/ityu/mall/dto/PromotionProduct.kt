package com.ityu.mall.dto


import com.ityu.mall.model.PmsProduct
import com.ityu.mall.model.PmsProductFullReduction
import com.ityu.mall.model.PmsProductLadder
import com.ityu.mall.model.PmsSkuStock

/**
 * Created by macro on 2018/8/27.
 * 商品的促销信息，包括sku、打折优惠、满减优惠
 */
class PromotionProduct : PmsProduct() {
    //商品库存信息
    var skuStockList: List<PmsSkuStock>? = null
    //商品打折信息
    var productLadderList: List<PmsProductLadder>? = null
    //商品满减信息
    var productFullReductionList: List<PmsProductFullReduction>? = null
}
