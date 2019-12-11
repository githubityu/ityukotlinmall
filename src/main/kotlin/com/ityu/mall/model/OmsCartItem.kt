package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

@Table(name = "oms_cart_item")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "OmsCartItem", description = "购物车表")
open class OmsCartItem : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var productId: Long? = null

    var productSkuId: Long? = null

    var memberId: Long? = null

    @ApiModelProperty(value = "购买数量")
    var quantity: Int? = null

    @ApiModelProperty(value = "添加到购物车的价格")
    var price: BigDecimal? = null

    @ApiModelProperty(value = "销售属性1")
    var sp1: String? = null

    @ApiModelProperty(value = "销售属性2")
    var sp2: String? = null

    @ApiModelProperty(value = "销售属性3")
    var sp3: String? = null

    @ApiModelProperty(value = "商品主图")
    var productPic: String? = null

    @ApiModelProperty(value = "商品名称")
    var productName: String? = null

    @ApiModelProperty(value = "商品副标题（卖点）")
    var productSubTitle: String? = null

    @ApiModelProperty(value = "商品sku条码")
    var productSkuCode: String? = null

    @ApiModelProperty(value = "会员昵称")
    var memberNickname: String? = null

    @ApiModelProperty(value = "创建时间")
    var createDate: Date? = null

    @ApiModelProperty(value = "修改时间")
    var modifyDate: Date? = null

    @ApiModelProperty(value = "是否删除")
    var deleteStatus: Int? = null

    @ApiModelProperty(value = "商品分类")
    var productCategoryId: Long? = null

    var productBrand: String? = null

    var productSn: String? = null

    @ApiModelProperty(value = "商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]")
    var productAttr: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productId=").append(productId)
        sb.append(", productSkuId=").append(productSkuId)
        sb.append(", memberId=").append(memberId)
        sb.append(", quantity=").append(quantity)
        sb.append(", price=").append(price)
        sb.append(", sp1=").append(sp1)
        sb.append(", sp2=").append(sp2)
        sb.append(", sp3=").append(sp3)
        sb.append(", productPic=").append(productPic)
        sb.append(", productName=").append(productName)
        sb.append(", productSubTitle=").append(productSubTitle)
        sb.append(", productSkuCode=").append(productSkuCode)
        sb.append(", memberNickname=").append(memberNickname)
        sb.append(", createDate=").append(createDate)
        sb.append(", modifyDate=").append(modifyDate)
        sb.append(", deleteStatus=").append(deleteStatus)
        sb.append(", productCategoryId=").append(productCategoryId)
        sb.append(", productBrand=").append(productBrand)
        sb.append(", productSn=").append(productSn)
        sb.append(", productAttr=").append(productAttr)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}