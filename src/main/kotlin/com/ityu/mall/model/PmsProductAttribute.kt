package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import java.io.Serializable
import javax.persistence.*


@Table(name = "pms_product_attribute")
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsProductAttribute", description = "商品属性参数表")
class PmsProductAttribute : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    var id: Long? = null

    var productAttributeCategoryId: Long? = null

    var name: String? = null

    @ApiModelProperty(value = "属性选择类型：0->唯一；1->单选；2->多选")
    var selectType: Int? = null

    @ApiModelProperty(value = "属性录入方式：0->手工录入；1->从列表中选取")
    var inputType: Int? = null

    @ApiModelProperty(value = "可选值列表，以逗号隔开")
    var inputList: String? = null

    @ApiModelProperty(value = "排序字段：最高的可以单独上传图片")
    var sort: Int? = null

    @ApiModelProperty(value = "分类筛选样式：1->普通；1->颜色")
    var filterType: Int? = null

    @ApiModelProperty(value = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    var searchType: Int? = null

    @ApiModelProperty(value = "相同属性产品是否关联；0->不关联；1->关联")
    var relatedStatus: Int? = null

    @ApiModelProperty(value = "是否支持手动新增；0->不支持；1->支持")
    var handAddStatus: Int? = null

    @ApiModelProperty(value = "属性的类型；0->规格；1->参数")
    var type: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", productAttributeCategoryId=").append(productAttributeCategoryId)
        sb.append(", name=").append(name)
        sb.append(", selectType=").append(selectType)
        sb.append(", inputType=").append(inputType)
        sb.append(", inputList=").append(inputList)
        sb.append(", sort=").append(sort)
        sb.append(", filterType=").append(filterType)
        sb.append(", searchType=").append(searchType)
        sb.append(", relatedStatus=").append(relatedStatus)
        sb.append(", handAddStatus=").append(handAddStatus)
        sb.append(", type=").append(type)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}