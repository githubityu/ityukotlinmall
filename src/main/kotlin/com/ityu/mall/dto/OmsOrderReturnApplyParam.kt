package com.ityu.mall.dto

import io.swagger.annotations.ApiModelProperty
import lombok.Getter
import lombok.Setter
import java.math.BigDecimal

/**
 * 申请退货参数
 * Created by macro on 2018/10/17.
 */

class OmsOrderReturnApplyParam {
    @ApiModelProperty("订单id")
    var orderId: Long? = null
    @ApiModelProperty("退货商品id")
    var productId: Long? = null
    @ApiModelProperty("订单编号")
    var orderSn: String? = null
    @ApiModelProperty("会员用户名")
    var memberUsername: String? = null
    @ApiModelProperty("退货人姓名")
    var returnName: String? = null
    @ApiModelProperty("退货人电话")
    var returnPhone: String? = null
    @ApiModelProperty("商品图片")
    var productPic: String? = null
    @ApiModelProperty("商品名称")
    var productName: String? = null
    @ApiModelProperty("商品品牌")
    var productBrand: String? = null
    @ApiModelProperty("商品销售属性：颜色：红色；尺码：xl;")
    var productAttr: String? = null
    @ApiModelProperty("退货数量")
    var productCount: Int? = null
    @ApiModelProperty("商品单价")
    var productPrice: BigDecimal? = null
    @ApiModelProperty("商品实际支付单价")
    var productRealPrice: BigDecimal? = null
    @ApiModelProperty("原因")
    var reason: String? = null
    @ApiModelProperty("描述")
    var description: String? = null
    @ApiModelProperty("凭证图片，以逗号隔开")
    var proofPics: String? = null
}