package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Table(name = "pms_product_ladder")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsProductLadder", description = "产品阶梯价格表(只针对同商品)")
class PmsProductLadder : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var productId: Long? = null

    @ApiModelProperty(value = "满足的商品数量")
    var count: Int? = null

    @ApiModelProperty(value = "折扣")
    var discount: BigDecimal? = null

    @ApiModelProperty(value = "折后价格")
    var price: BigDecimal? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productId=").append(productId)
        sb.append(", count=").append(count)
        sb.append(", discount=").append(discount)
        sb.append(", price=").append(price)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}