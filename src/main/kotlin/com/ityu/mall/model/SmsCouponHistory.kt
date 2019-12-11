package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.util.Date
import javax.persistence.*


@Table(name = "sms_coupon_history")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsCouponHistory", description = "优惠券使用、领取历史表")
open class SmsCouponHistory : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var couponId: Long? = null

    var memberId: Long? = null

    var couponCode: String? = null

    @ApiModelProperty(value = "领取人昵称")
    var memberNickname: String? = null

    @ApiModelProperty(value = "获取类型：0->后台赠送；1->主动获取")
    var getType: Int? = null

    var createTime: Date? = null

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    var useStatus: Int? = null

    @ApiModelProperty(value = "使用时间")
    var useTime: Date? = null

    @ApiModelProperty(value = "订单编号")
    var orderId: Long? = null

    @ApiModelProperty(value = "订单号码")
    var orderSn: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", couponId=").append(couponId)
        sb.append(", memberId=").append(memberId)
        sb.append(", couponCode=").append(couponCode)
        sb.append(", memberNickname=").append(memberNickname)
        sb.append(", getType=").append(getType)
        sb.append(", createTime=").append(createTime)
        sb.append(", useStatus=").append(useStatus)
        sb.append(", useTime=").append(useTime)
        sb.append(", orderId=").append(orderId)
        sb.append(", orderSn=").append(orderSn)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}