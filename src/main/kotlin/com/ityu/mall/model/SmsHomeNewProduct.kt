package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import javax.persistence.*

@Data
@Table(name = "sms_home_new_product")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "SmsHomeNewProduct", description = "新鲜好物表")
class SmsHomeNewProduct : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var productId: Long? = null

    var productName: String? = null

    var recommendStatus: Int? = null

    var sort: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productId=").append(productId)
        sb.append(", productName=").append(productName)
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