package com.ityu.mall.dto


import com.ityu.mall.model.PmsProduct
import com.ityu.mall.model.PmsProductAttribute
import com.ityu.mall.model.PmsSkuStock


/**
 * 购物车中选择规格的商品信息
 * Created by macro on 2018/8/2.
 */
class CartProduct : PmsProduct() {
    var productAttributeList: List<PmsProductAttribute>? = null
    var skuStockList: List<PmsSkuStock>? = null
}
