package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "pms_product_full_reduction")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsProductFullReduction", description = "产品满减表(只针对同商品)")
class PmsProductFullReduction : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var productId: Long? = null

    var fullPrice: BigDecimal? = null

    var reducePrice: BigDecimal? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productId=").append(productId)
        sb.append(", fullPrice=").append(fullPrice)
        sb.append(", reducePrice=").append(reducePrice)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}