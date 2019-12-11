package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*


@Data
@Table(name = "ums_member_level")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "UmsMemberLevel", description = "会员等级表")
class UmsMemberLevel : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    var id: Long? = null

    var name: String? = null

    var growthPoint: Int? = null

    @ApiModelProperty(value = "是否为默认等级：0->不是；1->是")
    var defaultStatus: Int? = null

    @ApiModelProperty(value = "免运费标准")
    var freeFreightPoint: BigDecimal? = null

    @ApiModelProperty(value = "每次评价获取的成长值")
    var commentGrowthPoint: Int? = null

    @ApiModelProperty(value = "是否有免邮特权")
    var priviledgeFreeFreight: Int? = null

    @ApiModelProperty(value = "是否有签到特权")
    var priviledgeSignIn: Int? = null

    @ApiModelProperty(value = "是否有评论获奖励特权")
    var priviledgeComment: Int? = null

    @ApiModelProperty(value = "是否有专享活动特权")
    var priviledgePromotion: Int? = null

    @ApiModelProperty(value = "是否有会员价格特权")
    var priviledgeMemberPrice: Int? = null

    @ApiModelProperty(value = "是否有生日特权")
    var priviledgeBirthday: Int? = null

    var note: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", name=").append(name)
        sb.append(", growthPoint=").append(growthPoint)
        sb.append(", defaultStatus=").append(defaultStatus)
        sb.append(", freeFreightPoint=").append(freeFreightPoint)
        sb.append(", commentGrowthPoint=").append(commentGrowthPoint)
        sb.append(", priviledgeFreeFreight=").append(priviledgeFreeFreight)
        sb.append(", priviledgeSignIn=").append(priviledgeSignIn)
        sb.append(", priviledgeComment=").append(priviledgeComment)
        sb.append(", priviledgePromotion=").append(priviledgePromotion)
        sb.append(", priviledgeMemberPrice=").append(priviledgeMemberPrice)
        sb.append(", priviledgeBirthday=").append(priviledgeBirthday)
        sb.append(", note=").append(note)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}