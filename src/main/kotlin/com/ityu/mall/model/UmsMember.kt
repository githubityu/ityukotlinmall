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
@Table(name = "ums_member")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "UmsMember", description = "会员表")
class UmsMember : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    var id: Long? = null
    var memberLevelId: Long? = null

    @ApiModelProperty(value = "用户名")
    var username: String? = null

    @ApiModelProperty(value = "密码")
    var password: String? = null

    @ApiModelProperty(value = "昵称")
    var nickname: String? = null

    @ApiModelProperty(value = "手机号码")
    var phone: String? = null

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    var status: Int? = null

    @ApiModelProperty(value = "注册时间")
    var createTime: Date? = null

    @ApiModelProperty(value = "头像")
    var icon: String? = null

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    var gender: Int? = null

    @ApiModelProperty(value = "生日")
    var birthday: Date? = null

    @ApiModelProperty(value = "所做城市")
    var city: String? = null

    @ApiModelProperty(value = "职业")
    var job: String? = null

    @ApiModelProperty(value = "个性签名")
    var personalizedSignature: String? = null

    @ApiModelProperty(value = "用户来源")
    var sourceType: Int? = null

    @ApiModelProperty(value = "积分")
    var integration: Int? = null

    @ApiModelProperty(value = "成长值")
    var growth: Int? = null

    @ApiModelProperty(value = "剩余抽奖次数")
    var luckeyCount: Int? = null

    @ApiModelProperty(value = "历史积分数量")
    var historyIntegration: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", memberLevelId=").append(memberLevelId)
        sb.append(", username=").append(username)
        sb.append(", password=").append(password)
        sb.append(", nickname=").append(nickname)
        sb.append(", phone=").append(phone)
        sb.append(", status=").append(status)
        sb.append(", createTime=").append(createTime)
        sb.append(", icon=").append(icon)
        sb.append(", gender=").append(gender)
        sb.append(", birthday=").append(birthday)
        sb.append(", city=").append(city)
        sb.append(", job=").append(job)
        sb.append(", personalizedSignature=").append(personalizedSignature)
        sb.append(", sourceType=").append(sourceType)
        sb.append(", integration=").append(integration)
        sb.append(", growth=").append(growth)
        sb.append(", luckeyCount=").append(luckeyCount)
        sb.append(", historyIntegration=").append(historyIntegration)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}