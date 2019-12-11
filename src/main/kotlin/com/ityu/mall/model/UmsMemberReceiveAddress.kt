package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Table(name = "ums_member_receive_address")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "UmsMemberReceiveAddress", description = "会员收货地址表")
class UmsMemberReceiveAddress : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var memberId: Long? = null

    @ApiModelProperty(value = "收货人名称")
    var name: String? = null

    var phoneNumber: String? = null

    @ApiModelProperty(value = "是否为默认")
    var defaultStatus: Int? = null

    @ApiModelProperty(value = "邮政编码")
    var postCode: String? = null

    @ApiModelProperty(value = "省份/直辖市")
    var province: String? = null

    @ApiModelProperty(value = "城市")
    var city: String? = null

    @ApiModelProperty(value = "区")
    var region: String? = null

    @ApiModelProperty(value = "详细地址(街道)")
    var detailAddress: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", memberId=").append(memberId)
        sb.append(", name=").append(name)
        sb.append(", phoneNumber=").append(phoneNumber)
        sb.append(", defaultStatus=").append(defaultStatus)
        sb.append(", postCode=").append(postCode)
        sb.append(", province=").append(province)
        sb.append(", city=").append(city)
        sb.append(", region=").append(region)
        sb.append(", detailAddress=").append(detailAddress)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}