package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import javax.persistence.*
import java.io.Serializable


@Data
@Table(name = "pms_brand")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsBrand", description = "品牌表")
class PmsBrand : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    var id: Long? = null

    var name: String? = null

    @ApiModelProperty(value = "首字母")
    var firstLetter: String? = null

    var sort: Int? = null

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    var factoryStatus: Int? = null

    var showStatus: Int? = null

    @ApiModelProperty(value = "产品数量")
    var productCount: Int? = null

    @ApiModelProperty(value = "产品评论数量")
    var productCommentCount: Int? = null

    @ApiModelProperty(value = "品牌logo")
    var logo: String? = null

    @ApiModelProperty(value = "专区大图")
    var bigPic: String? = null

    @ApiModelProperty(value = "品牌故事")
    var brandStory: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", name=").append(name)
        sb.append(", firstLetter=").append(firstLetter)
        sb.append(", sort=").append(sort)
        sb.append(", factoryStatus=").append(factoryStatus)
        sb.append(", showStatus=").append(showStatus)
        sb.append(", productCount=").append(productCount)
        sb.append(", productCommentCount=").append(productCommentCount)
        sb.append(", logo=").append(logo)
        sb.append(", bigPic=").append(bigPic)
        sb.append(", brandStory=").append(brandStory)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}