package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Data
@Table(name = "sms_flash_promotion_product_relation")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsFlashPromotionProductRelation", description = "商品限时购与商品关系表")
class SmsFlashPromotionProductRelation : Serializable {
    @ApiModelProperty(value = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var flashPromotionId: Long? = null

    @ApiModelProperty(value = "编号")
    var flashPromotionSessionId: Long? = null

    var productId: Long? = null

    @ApiModelProperty(value = "限时购价格")
    var flashPromotionPrice: BigDecimal? = null

    @ApiModelProperty(value = "限时购数量")
    var flashPromotionCount: Int? = null

    @ApiModelProperty(value = "每人限购数量")
    var flashPromotionLimit: Int? = null

    @ApiModelProperty(value = "排序")
    var sort: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", flashPromotionId=").append(flashPromotionId)
        sb.append(", flashPromotionSessionId=").append(flashPromotionSessionId)
        sb.append(", productId=").append(productId)
        sb.append(", flashPromotionPrice=").append(flashPromotionPrice)
        sb.append(", flashPromotionCount=").append(flashPromotionCount)
        sb.append(", flashPromotionLimit=").append(flashPromotionLimit)
        sb.append(", sort=").append(sort)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}