package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "pms_sku_stock")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsSkuStock", description = "sku的库存")
class PmsSkuStock : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var productId: Long? = null

    @ApiModelProperty(value = "sku编码")
    var skuCode: String? = null

    var price: BigDecimal? = null

    @ApiModelProperty(value = "库存")
    var stock: Int? = null

    @ApiModelProperty(value = "预警库存")
    var lowStock: Int? = null

    @ApiModelProperty(value = "销售属性1")
    var sp1: String? = null

    var sp2: String? = null

    var sp3: String? = null

    @ApiModelProperty(value = "展示图片")
    var pic: String? = null

    @ApiModelProperty(value = "销量")
    var sale: Int? = null

    @ApiModelProperty(value = "单品促销价格")
    var promotionPrice: BigDecimal? = null

    @ApiModelProperty(value = "锁定库存")
    var lockStock: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productId=").append(productId)
        sb.append(", skuCode=").append(skuCode)
        sb.append(", price=").append(price)
        sb.append(", stock=").append(stock)
        sb.append(", lowStock=").append(lowStock)
        sb.append(", sp1=").append(sp1)
        sb.append(", sp2=").append(sp2)
        sb.append(", sp3=").append(sp3)
        sb.append(", pic=").append(pic)
        sb.append(", sale=").append(sale)
        sb.append(", promotionPrice=").append(promotionPrice)
        sb.append(", lockStock=").append(lockStock)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}