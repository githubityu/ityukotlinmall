package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Table(name = "sms_coupon_product_category_relation")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsCouponProductCategoryRelation", description = "优惠券和产品分类关系表")
class SmsCouponProductCategoryRelation : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var couponId: Long? = null

    var productCategoryId: Long? = null

    @ApiModelProperty(value = "产品分类名称")
    var productCategoryName: String? = null

    @ApiModelProperty(value = "父分类名称")
    var parentCategoryName: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", couponId=").append(couponId)
        sb.append(", productCategoryId=").append(productCategoryId)
        sb.append(", productCategoryName=").append(productCategoryName)
        sb.append(", parentCategoryName=").append(parentCategoryName)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}