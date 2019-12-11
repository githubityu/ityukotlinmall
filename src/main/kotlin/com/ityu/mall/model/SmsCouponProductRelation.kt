package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Table(name = "sms_coupon_product_relation")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsCouponProductRelation", description = "优惠券和产品的关系表")
class SmsCouponProductRelation : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var couponId: Long? = null

    var productId: Long? = null

    @ApiModelProperty(value = "商品名称")
    var productName: String? = null

    @ApiModelProperty(value = "商品编码")
    var productSn: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", couponId=").append(couponId)
        sb.append(", productId=").append(productId)
        sb.append(", productName=").append(productName)
        sb.append(", productSn=").append(productSn)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}