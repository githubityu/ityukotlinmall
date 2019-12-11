package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import javax.persistence.*

@Data
@Table(name = "sms_home_brand")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsHomeBrand", description = "首页推荐品牌表")
class SmsHomeBrand : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var brandId: Long? = null

    var brandName: String? = null

    var recommendStatus: Int? = null

    var sort: Int? = null


    @Transient
    var recommendsBrand:List<PmsBrand> = mutableListOf()

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", brandId=").append(brandId)
        sb.append(", brandName=").append(brandName)
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