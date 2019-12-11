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
@Table(name = "sms_flash_promotion")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsFlashPromotion", description = "限时购表")
class SmsFlashPromotion : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var title: String? = null

    @ApiModelProperty(value = "开始日期")
    var startDate: Date? = null

    @ApiModelProperty(value = "结束日期")
    var endDate: Date? = null

    @ApiModelProperty(value = "上下线状态")
    var status: Int? = null

    @ApiModelProperty(value = "秒杀时间段名称")
    var createTime: Date? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", title=").append(title)
        sb.append(", startDate=").append(startDate)
        sb.append(", endDate=").append(endDate)
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