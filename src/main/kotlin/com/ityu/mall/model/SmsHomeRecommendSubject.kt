package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import javax.persistence.*

@Data
@Table(name = "sms_home_recommend_subject")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsHomeRecommendSubject", description = "首页推荐专题表")
class SmsHomeRecommendSubject : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var subjectId: Long? = null

    var subjectName: String? = null

    var recommendStatus: Int? = null

    var sort: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", subjectId=").append(subjectId)
        sb.append(", subjectName=").append(subjectName)
        sb.append(", recommendStatus=").append(recommendStatus)
        sb.append(", sort=").append(sort)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}