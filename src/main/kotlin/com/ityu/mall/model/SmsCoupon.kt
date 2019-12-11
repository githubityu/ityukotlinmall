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


@Table(name = "sms_coupon")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsCoupon", description = "优惠卷表")
class SmsCoupon : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ApiModelProperty(value = "优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    var type: Int? = null

    var name: String? = null

    @ApiModelProperty(value = "使用平台：0->全部；1->移动；2->PC")
    var platform: Int? = null

    @ApiModelProperty(value = "数量")
    var count: Int? = null

    @ApiModelProperty(value = "金额")
    var amount: BigDecimal? = null

    @ApiModelProperty(value = "每人限领张数")
    var perLimit: Int? = null

    @ApiModelProperty(value = "使用门槛；0表示无门槛")
    var minPoint: BigDecimal? = null

    var startTime: Date? = null

    var endTime: Date? = null

    @ApiModelProperty(value = "使用类型：0->全场通用；1->指定分类；2->指定商品")
    var useType: Int? = null

    @ApiModelProperty(value = "备注")
    var note: String? = null

    @ApiModelProperty(value = "发行数量")
    var publishCount: Int? = null

    @ApiModelProperty(value = "已使用数量")
    var useCount: Int? = null

    @ApiModelProperty(value = "领取数量")
    var receiveCount: Int? = null

    @ApiModelProperty(value = "可以领取的日期")
    var enableTime: Date? = null

    @ApiModelProperty(value = "优惠码")
    var code: String? = null

    @ApiModelProperty(value = "可领取的会员类型：0->无限时")
    var memberLevel: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", type=").append(type)
        sb.append(", name=").append(name)
        sb.append(", platform=").append(platform)
        sb.append(", count=").append(count)
        sb.append(", amount=").append(amount)
        sb.append(", perLimit=").append(perLimit)
        sb.append(", minPoint=").append(minPoint)
        sb.append(", startTime=").append(startTime)
        sb.append(", endTime=").append(endTime)
        sb.append(", useType=").append(useType)
        sb.append(", note=").append(note)
        sb.append(", publishCount=").append(publishCount)
        sb.append(", useCount=").append(useCount)
        sb.append(", receiveCount=").append(receiveCount)
        sb.append(", enableTime=").append(enableTime)
        sb.append(", code=").append(code)
        sb.append(", memberLevel=").append(memberLevel)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}