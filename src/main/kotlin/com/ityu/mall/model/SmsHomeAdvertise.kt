package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.util.Date
import javax.persistence.*


@Data
@Table(name = "sms_home_advertise")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsHomeAdvertise", description = "首页轮播广告表")
class SmsHomeAdvertise : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var name: String? = null

    @ApiModelProperty(value = "轮播位置：0->PC首页轮播；1->app首页轮播")
    var type: Int? = null

    var pic: String? = null

    var startTime: Date? = null

    var endTime: Date? = null

    @ApiModelProperty(value = "上下线状态：0->下线；1->上线")
    var status: Int? = null

    @ApiModelProperty(value = "点击数")
    var clickCount: Int? = null

    @ApiModelProperty(value = "下单数")
    var orderCount: Int? = null

    @ApiModelProperty(value = "链接地址")
    var url: String? = null

    @ApiModelProperty(value = "备注")
    var note: String? = null

    @ApiModelProperty(value = "排序")
    var sort: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", name=").append(name)
        sb.append(", type=").append(type)
        sb.append(", pic=").append(pic)
        sb.append(", startTime=").append(startTime)
        sb.append(", endTime=").append(endTime)
        sb.append(", status=").append(status)
        sb.append(", clickCount=").append(clickCount)
        sb.append(", orderCount=").append(orderCount)
        sb.append(", url=").append(url)
        sb.append(", note=").append(note)
        sb.append(", sort=").append(sort)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}