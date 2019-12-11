package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Table(name = "oms_order_setting")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "OmsOrderSetting", description = "订单设置表")
class OmsOrderSetting : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    @ApiModelProperty(value = "秒杀订单超时关闭时间(分)")
    var flashOrderOvertime: Int? = null

    @ApiModelProperty(value = "正常订单超时时间(分)")
    var normalOrderOvertime: Int? = null

    @ApiModelProperty(value = "发货后自动确认收货时间（天）")
    var confirmOvertime: Int? = null

    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）")
    var finishOvertime: Int? = null

    @ApiModelProperty(value = "订单完成后自动好评时间（天）")
    var commentOvertime: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", flashOrderOvertime=").append(flashOrderOvertime)
        sb.append(", normalOrderOvertime=").append(normalOrderOvertime)
        sb.append(", confirmOvertime=").append(confirmOvertime)
        sb.append(", finishOvertime=").append(finishOvertime)
        sb.append(", commentOvertime=").append(commentOvertime)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}