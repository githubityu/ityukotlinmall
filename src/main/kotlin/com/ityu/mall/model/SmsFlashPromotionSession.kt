package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.sql.Time
import java.util.Date
import javax.persistence.*

@Data
@Table(name = "sms_flash_promotion_session")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsFlashPromotionSession", description = "限时购场次表")
class SmsFlashPromotionSession : Serializable {
    @ApiModelProperty(value = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    @ApiModelProperty(value = "场次名称")
    var name: String? = null

    @ApiModelProperty(value = "每日开始时间")
    var startTime: Time? = null

    @ApiModelProperty(value = "每日结束时间")
    var endTime: Time? = null

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    var status: Int? = null

    @ApiModelProperty(value = "创建时间")
    var createTime: Date? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", name=").append(name)
        sb.append(", startTime=").append(startTime)
        sb.append(", endTime=").append(endTime)
        sb.append(", status=").append(status)
        sb.append(", createTime=").append(createTime)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}