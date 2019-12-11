package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Table(name = "ums_integration_consume_setting")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "UmsIntegrationConsumeSetting", description = "积分消费设置")
class UmsIntegrationConsumeSetting : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ApiModelProperty(value = "每一元需要抵扣的积分数量")
    var deductionPerAmount: Int? = null

    @ApiModelProperty(value = "每笔订单最高抵用百分比")
    var maxPercentPerOrder: Int? = null

    @ApiModelProperty(value = "每次使用积分最小单位100")
    var useUnit: Int? = null

    @ApiModelProperty(value = "是否可以和优惠券同用；0->不可以；1->可以")
    var couponStatus: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", deductionPerAmount=").append(deductionPerAmount)
        sb.append(", maxPercentPerOrder=").append(maxPercentPerOrder)
        sb.append(", useUnit=").append(useUnit)
        sb.append(", couponStatus=").append(couponStatus)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}