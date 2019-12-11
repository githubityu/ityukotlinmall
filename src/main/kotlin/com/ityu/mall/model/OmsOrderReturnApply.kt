package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Table(name = "oms_order_return_apply")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "OmsOrderReturnApply", description = "订单退货申请")
class OmsOrderReturnApply : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null
    @ApiModelProperty(value = "订单id")
    var orderId: Long? = null
    @ApiModelProperty(value = "收货地址表id")
    var companyAddressId: Long? = null
    @ApiModelProperty(value = "退货商品id")
    var productId: Long? = null
    @ApiModelProperty(value = "订单编号")
    var orderSn: String? = null
    @ApiModelProperty(value = "申请时间")
    var createTime: Date? = null
    @ApiModelProperty(value = "会员用户名")
    var memberUsername: String? = null
    @ApiModelProperty(value = "退款金额")
    var returnAmount: BigDecimal? = null
    @ApiModelProperty(value = "退货人姓名")
    var returnName: String? = null
    @ApiModelProperty(value = "退货人电话")
    var returnPhone: String? = null
    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    var status: Int? = null
    @ApiModelProperty(value = "处理时间")
    var handleTime: Date? = null
    @ApiModelProperty(value = "商品图片")
    var productPic: String? = null
    @ApiModelProperty(value = "商品名称")
    var productName: String? = null
    @ApiModelProperty(value = "商品品牌")
    var productBrand: String? = null
    @ApiModelProperty(value = "商品销售属性：颜色：红色；尺码：xl;")
    var productAttr: String? = null
    @ApiModelProperty(value = "退货数量")
    var productCount: Int? = null
    @ApiModelProperty(value = "商品单价")
    var productPrice: BigDecimal? = null
    @ApiModelProperty(value = "商品实际支付单价")
    var productRealPrice: BigDecimal? = null
    @ApiModelProperty(value = "原因")
    var reason: String? = null
    @ApiModelProperty(value = "描述")
    var description: String? = null
    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    var proofPics: String? = null
    @ApiModelProperty(value = "处理备注")
    var handleNote: String? = null
    @ApiModelProperty(value = "处理人员")
    var handleMan: String? = null
    @ApiModelProperty(value = "收货人")
    var receiveMan: String? = null
    @ApiModelProperty(value = "收货时间")
    var receiveTime: Date? = null
    @ApiModelProperty(value = "收货备注")
    var receiveNote: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", orderId=").append(orderId)
        sb.append(", companyAddressId=").append(companyAddressId)
        sb.append(", productId=").append(productId)
        sb.append(", orderSn=").append(orderSn)
        sb.append(", createTime=").append(createTime)
        sb.append(", memberUsername=").append(memberUsername)
        sb.append(", returnAmount=").append(returnAmount)
        sb.append(", returnName=").append(returnName)
        sb.append(", returnPhone=").append(returnPhone)
        sb.append(", status=").append(status)
        sb.append(", handleTime=").append(handleTime)
        sb.append(", productPic=").append(productPic)
        sb.append(", productName=").append(productName)
        sb.append(", productBrand=").append(productBrand)
        sb.append(", productAttr=").append(productAttr)
        sb.append(", productCount=").append(productCount)
        sb.append(", productPrice=").append(productPrice)
        sb.append(", productRealPrice=").append(productRealPrice)
        sb.append(", reason=").append(reason)
        sb.append(", description=").append(description)
        sb.append(", proofPics=").append(proofPics)
        sb.append(", handleNote=").append(handleNote)
        sb.append(", handleMan=").append(handleMan)
        sb.append(", receiveMan=").append(receiveMan)
        sb.append(", receiveTime=").append(receiveTime)
        sb.append(", receiveNote=").append(receiveNote)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}