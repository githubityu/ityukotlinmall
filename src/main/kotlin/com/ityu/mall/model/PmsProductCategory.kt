package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*

@Data
@Table(name = "pms_product_category")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsProductCategory", description = "产品分类")
class PmsProductCategory : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null


    @ApiModelProperty(value = "上机分类的编号：0表示一级分类")
    var parentId: Long? = null

    var name: String? = null

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    var level: Int? = null

    var productCount: Int? = null

    var productUnit: String? = null

    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示")
    var navStatus: Int? = null

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    var showStatus: Int? = null

    var sort: Int? = null

    @ApiModelProperty(value = "图标")
    var icon: String? = null

    var keywords: String? = null

    @ApiModelProperty(value = "描述")
    var description: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", parentId=").append(parentId)
        sb.append(", name=").append(name)
        sb.append(", level=").append(level)
        sb.append(", productCount=").append(productCount)
        sb.append(", productUnit=").append(productUnit)
        sb.append(", navStatus=").append(navStatus)
        sb.append(", showStatus=").append(showStatus)
        sb.append(", sort=").append(sort)
        sb.append(", icon=").append(icon)
        sb.append(", keywords=").append(keywords)
        sb.append(", description=").append(description)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}