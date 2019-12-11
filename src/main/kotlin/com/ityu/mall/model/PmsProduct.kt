package com.ityu.mall.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.experimental.Accessors
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

import javax.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.util.Date

@Data
@Table(name = "pms_product")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
@Entity
@ApiModel(value = "PmsProduct", description = "商品信息")
open class PmsProduct : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    var id: Long? = null

    var brandId: Long? = null

    var productCategoryId: Long? = null

    var feightTemplateId: Long? = null

    var productAttributeCategoryId: Long? = null

    var name: String? = null

    var pic: String? = null

    @ApiModelProperty(value = "货号")
    var productSn: String? = null

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    var deleteStatus: Int? = null

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    var publishStatus: Int? = null

    @ApiModelProperty(value = "新品状态:0->不是新品；1->新品")
    var newStatus: Int? = null

    @ApiModelProperty(value = "推荐状态；0->不推荐；1->推荐")
    var recommandStatus: Int? = null

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    var verifyStatus: Int? = null

    @ApiModelProperty(value = "排序")
    var sort: Int? = null

    @ApiModelProperty(value = "销量")
    var sale: Int? = null

    var price: BigDecimal? = null

    @ApiModelProperty(value = "促销价格")
    var promotionPrice: BigDecimal? = null

    @ApiModelProperty(value = "赠送的成长值")
    var giftGrowth: Int? = null

    @ApiModelProperty(value = "赠送的积分")
    var giftPoint: Int? = null

    @ApiModelProperty(value = "限制使用的积分数")
    var usePointLimit: Int? = null

    @ApiModelProperty(value = "副标题")
    var subTitle: String? = null

    @ApiModelProperty(value = "市场价")
    var originalPrice: BigDecimal? = null

    @ApiModelProperty(value = "库存")
    var stock: Int? = null

    @ApiModelProperty(value = "库存预警值")
    var lowStock: Int? = null

    @ApiModelProperty(value = "单位")
    var unit: String? = null

    @ApiModelProperty(value = "商品重量，默认为克")
    var weight: BigDecimal? = null

    @ApiModelProperty(value = "是否为预告商品：0->不是；1->是")
    var previewStatus: Int? = null

    @ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    var serviceIds: String? = null

    var keywords: String? = null

    var note: String? = null

    @ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
    var albumPics: String? = null

    var detailTitle: String? = null

    @ApiModelProperty(value = "促销开始时间")
    var promotionStartTime: Date? = null

    @ApiModelProperty(value = "促销结束时间")
    var promotionEndTime: Date? = null

    @ApiModelProperty(value = "活动限购数量")
    var promotionPerLimit: Int? = null

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    var promotionType: Int? = null

    @ApiModelProperty(value = "品牌名称")
    var brandName: String? = null

    @ApiModelProperty(value = "商品分类名称")
    var productCategoryName: String? = null

    @ApiModelProperty(value = "商品描述")
    var description: String? = null

    var detailDesc: String? = null

    @ApiModelProperty(value = "产品详情网页内容")
    var detailHtml: String? = null

    @ApiModelProperty(value = "移动端网页详情")
    var detailMobileHtml: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(javaClass.simpleName)
        sb.append(" [")
        sb.append("Hash = ").append(hashCode())
        sb.append(", id=").append(id)
        sb.append(", brandId=").append(brandId)
        sb.append(", productCategoryId=").append(productCategoryId)
        sb.append(", feightTemplateId=").append(feightTemplateId)
        sb.append(", productAttributeCategoryId=").append(productAttributeCategoryId)
        sb.append(", name=").append(name)
        sb.append(", pic=").append(pic)
        sb.append(", productSn=").append(productSn)
        sb.append(", deleteStatus=").append(deleteStatus)
        sb.append(", publishStatus=").append(publishStatus)
        sb.append(", newStatus=").append(newStatus)
        sb.append(", recommandStatus=").append(recommandStatus)
        sb.append(", verifyStatus=").append(verifyStatus)
        sb.append(", sort=").append(sort)
        sb.append(", sale=").append(sale)
        sb.append(", price=").append(price)
        sb.append(", promotionPrice=").append(promotionPrice)
        sb.append(", giftGrowth=").append(giftGrowth)
        sb.append(", giftPoint=").append(giftPoint)
        sb.append(", usePointLimit=").append(usePointLimit)
        sb.append(", subTitle=").append(subTitle)
        sb.append(", originalPrice=").append(originalPrice)
        sb.append(", stock=").append(stock)
        sb.append(", lowStock=").append(lowStock)
        sb.append(", unit=").append(unit)
        sb.append(", weight=").append(weight)
        sb.append(", previewStatus=").append(previewStatus)
        sb.append(", serviceIds=").append(serviceIds)
        sb.append(", keywords=").append(keywords)
        sb.append(", note=").append(note)
        sb.append(", albumPics=").append(albumPics)
        sb.append(", detailTitle=").append(detailTitle)
        sb.append(", promotionStartTime=").append(promotionStartTime)
        sb.append(", promotionEndTime=").append(promotionEndTime)
        sb.append(", promotionPerLimit=").append(promotionPerLimit)
        sb.append(", promotionType=").append(promotionType)
        sb.append(", brandName=").append(brandName)
        sb.append(", productCategoryName=").append(productCategoryName)
        sb.append(", description=").append(description)
        sb.append(", detailDesc=").append(detailDesc)
        sb.append(", detailHtml=").append(detailHtml)
        sb.append(", detailMobileHtml=").append(detailMobileHtml)
        sb.append(", serialVersionUID=").append(serialVersionUID)
        sb.append("]")
        return sb.toString()
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}